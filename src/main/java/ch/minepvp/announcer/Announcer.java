package ch.minepvp.announcer;

import ch.minepvp.announcer.caller.BukkitCaller;
import ch.minepvp.announcer.caller.Caller;
import ch.minepvp.announcer.command.manager.CommandManager;
import ch.minepvp.announcer.config.BukkitConfig;
import ch.minepvp.announcer.config.Config;
import ch.minepvp.announcer.config.SpoutConfig;
import ch.minepvp.announcer.loader.Loader;
import ch.minepvp.announcer.manager.MessageGroupManager;
import ch.minepvp.announcer.manager.mysql.MySQLMessageGroupManager;
import ch.minepvp.announcer.manager.yaml.YamlMessageGroupManager;
import ch.minepvp.announcer.caller.SpoutCaller;

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

            Announcer.getInstance().getLogger().info("config.has?");


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




    }

    private void setupMetrics() {

        /*
        Metrics metrics = new Metrics() {
            @Override
            public String getFullServerVersion() {
                return getCaller().getFullServerVersion();
            }

            @Override
            public int getPlayersOnline() {
                return getCaller().getPlayersOnline();
            }

            @Override
            public File getConfigFile() {
                return getConfig();
            }

        };
        */

        getLogger().info("loaded Metrics");
    }

    private void disable() {
        messageGroupManager.stopTasks();
        messageGroupManager.save();

        getLogger().info("disabled");
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

}
