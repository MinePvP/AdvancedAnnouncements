package ch.minepvp.announcer;

import ch.minepvp.announcer.caller.BukkitCaller;
import ch.minepvp.announcer.caller.Caller;
import ch.minepvp.announcer.command.CommandHandler;
import ch.minepvp.announcer.command.manager.CommandManager;
import ch.minepvp.announcer.command.manager.announcer.*;
import ch.minepvp.announcer.command.manager.bukkit.BukkitCommandManager;
import ch.minepvp.announcer.command.manager.spout.SpoutCommandManager;
import ch.minepvp.announcer.config.BukkitConfig;
import ch.minepvp.announcer.config.Config;
import ch.minepvp.announcer.config.SpoutConfig;
import ch.minepvp.announcer.loader.Loader;
import ch.minepvp.announcer.manager.MessageGroupManager;
import ch.minepvp.announcer.manager.mysql.MySQLMessageGroupManager;
import ch.minepvp.announcer.manager.yaml.YamlMessageGroupManager;
import ch.minepvp.announcer.caller.SpoutCaller;
import org.mcstats.Metrics;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Announcer {

    private static Announcer instance;

    private Logger logger;

    private Caller caller;

    // Configuration
    private Config config;

    // Manager
    private MessageGroupManager messageGroupManager;
    private CommandManager commandManager;

    // Commands
    private Map<String, CommandHandler> commands;

    public Announcer( Loader loader, Logger logger ) {
        instance = this;
        this.logger = logger;

        if ( loader.getServerType().equals( ServerType.SPOUT ) ) {

            caller = new SpoutCaller(loader);


        } else if ( loader.getServerType().equals( ServerType.BUKKIT ) ) {

            caller = new BukkitCaller(loader);

        }

        initialize();
    }

    private void initialize() {

        setupConfig();

        // Manager
        if ( getConfig().getBoolean("Settings.MySQL.Enabled") ) {
            messageGroupManager = new MySQLMessageGroupManager();
        } else {
            messageGroupManager = new YamlMessageGroupManager();
        }

        setupCommands();

        setupMetrics();
    }

    private void setupConfig() {

        // Config
        if ( getCaller().getLoader().getServerType().equals( ServerType.SPOUT ) ) {
            config = new SpoutConfig( getCaller().getDataFolder() );
        } else if ( getCaller().getLoader().getServerType().equals( ServerType.BUKKIT ) ) {
            config = new BukkitConfig();
        }

        // load Defaults
        if ( !getConfig().has("Settings.Interval") ) {
            setupDefaultConfig();
        }

        getLogger().info("loaded Config");
    }

    private void setupDefaultConfig() {

        // Default Interval and Prefix
        config.setValue("Settings.Interval", 60);
        config.setValue("Settings.Prefix", "{{RED}}Announce {{WHITE}}: ");

        // MySQL
        config.setValue("Settings.MySQL.Enabled", false);
        config.setValue("Settings.MySQL.Hostname", "localhost");
        config.setValue("Settings.MySQL.Port", 3306);
        config.setValue("Settings.MySQL.Database", "database");
        config.setValue("Settings.MySQL.Username", "username");
        config.setValue("Settings.MySQL.Password", "password");

    }

    private void setupCommands() {

        if ( getCaller().getLoader().getServerType().equals( ServerType.SPOUT ) ) {
             commandManager = new SpoutCommandManager();
        } else if ( getCaller().getLoader().getServerType().equals( ServerType.BUKKIT ) ) {
            commandManager = new BukkitCommandManager();
        }

        commands = new HashMap<String, CommandHandler>();


        // Announcer Commands
        CommandHandler announcerCommand = new CommandHandler("announcer", "Main Command");
        announcerCommand.registerSubCommand("help", new HelpCommand());
        announcerCommand.registerSubCommand("list", new ListCommand());
        announcerCommand.registerSubCommand("new", new NewCommand());
        announcerCommand.registerSubCommand("delete", new DeleteCommand());

        announcerCommand.registerSubCommand("addmessage", new AddMessageCommand());
        announcerCommand.registerSubCommand("adm", new AddMessageCommand());
        announcerCommand.registerSubCommand("removemessage", new RemoveMessageCommand());
        announcerCommand.registerSubCommand("rmm", new RemoveMessageCommand());

        announcerCommand.registerSubCommand("addworld", new AddWorldCommand());
        announcerCommand.registerSubCommand("adw", new AddWorldCommand());
        announcerCommand.registerSubCommand("removeworld", new RemoveWorldCommand());
        announcerCommand.registerSubCommand("rmw", new RemoveWorldCommand());

        announcerCommand.registerSubCommand("setpermission", new SetPermissionCommand());
        announcerCommand.registerSubCommand("setperm", new SetPermissionCommand());

        announcerCommand.registerSubCommand("setrandom", new SetRandomCommand());
        announcerCommand.registerSubCommand("setrand", new SetRandomCommand());

        announcerCommand.registerSubCommand("setprefix", new SetPrefixCommand());
        announcerCommand.registerSubCommand("setpre", new SetPrefixCommand());

        announcerCommand.registerSubCommand("setinterval", new SetInterval());
        announcerCommand.registerSubCommand("setint", new SetInterval());

        commands.put("announcer", announcerCommand);

    }

    private void setupMetrics() {

        try {

            Metrics metrics = new Metrics("Announcer", getCaller().getPluginVersion()) {

                @Override
                public String getFullServerVersion() {
                    return getCaller().getFullServerVersion();
                }

                @Override
                public int getPlayersOnline() {
                    return getCaller().getOnlinePlayers();
                }

                @Override
                public File getConfigFile() {
                    return getConfig().getFile();
                }

            };

            metrics.start();

            getLogger().info("loaded Metrics");
        } catch (IOException e) {
            getLogger().info("Error with Metrics");
        }

    }

    public void disable() {
        messageGroupManager.stopTasks();
        messageGroupManager.save();
    }

    public static Announcer getInstance() {
        return instance;
    }

    public Caller getCaller() {
        return caller;
    }

    public Config getConfig() {
        return config;
    }

    public Logger getLogger() {
        return logger;
    }

    public MessageGroupManager getMessageGroupManager() {
        return messageGroupManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public boolean commandExist( String name ) {
        return commands.containsKey(name);
    }

    public CommandHandler getCommandHandler( String name ) {

        if ( commandExist(name) ) {
            return commands.get(name);
        }

        return null;
    }

}
