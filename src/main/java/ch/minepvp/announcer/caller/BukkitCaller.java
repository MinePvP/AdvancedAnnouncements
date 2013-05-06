package ch.minepvp.announcer.caller;

import ch.minepvp.announcer.command.manager.CommandManager;
import ch.minepvp.announcer.command.manager.bukkit.BukkitCommandManager;
import ch.minepvp.announcer.loader.Loader;
import ch.minepvp.announcer.loader.BukkitLoader;
import ch.minepvp.announcer.messagegroup.MessageGroup;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;

public class BukkitCaller implements Caller {

    private BukkitLoader loader;

    public BukkitCaller( Loader loader ) {
        this.loader = (BukkitLoader) loader;
    }

    @Override
    public Loader getLoader() {
        return loader;
    }

    @Override
    public File getDataFolder() {
        return loader.getDataFolder();
    }

    @Override
    public String getFullServerVersion() {
        return loader.getServer().getVersion();
    }

    @Override
    public String getPluginVersion() {
        return loader.getDescription().getVersion();
    }

    @Override
    public Integer getOnlinePlayers() {
        return loader.getServer().getOnlinePlayers().length;
    }

    @Override
    public boolean hasPermission(String name, String permission) {

        Player player = loader.getServer().getPlayerExact(name);

        // Console
        if ( player == null ) {
            return true;
        }

        return player.hasPermission( permission );

    }

    @Override
    public boolean hasWorld(String world) {

        if ( loader.getServer().getWorld(world) != null ) {
            return true;
        }

        return false;
    }

    @Override
    public void sendMessage(String name, String message) {

        Player player = loader.getServer().getPlayerExact(name);

        // Console
        if ( player == null ) {
            loader.getServer().getConsoleSender().sendMessage( message );
        }

        player.sendMessage( format(message) );

    }

    @Override
    public void sendAnnouncment(MessageGroup messageGroup, String prefix, Integer counter) {

        for (Player player : loader.getServer().getOnlinePlayers() ) {

            Boolean send = false;

            // Is in one of the Worlds?
            if ( messageGroup.getWorlds().size() > 0 ) {

                for ( String world : messageGroup.getWorlds() ) {

                    if ( !world.equalsIgnoreCase("") ) {

                        if ( player.getWorld().getName().equalsIgnoreCase( world ) ) {
                            send = true;
                        }

                    }

                }

            } else {
                send = true;
            }

            // Has the Permission?
            if ( !messageGroup.getPermission().equalsIgnoreCase("") ) {

                send = player.hasPermission( messageGroup.getPermission() );

            } else {
                send = true;
            }

            // ChatChannel not Supported in Bukkit
            // Local not Supported in Bukkit


            // Send the Message
            if ( send ) {

                String[] lines = messageGroup.getMessages().get( counter ).split("<newline>");

                if ( lines.length > 0 ) {

                    for ( String line : lines ) {
                        player.sendMessage( format(prefix + line) );
                    }

                } else {
                    player.sendMessage( format(prefix + messageGroup.getMessages().get(counter)) );
                }

            }

        }

        // TODO Debug
        loader.getLogger().info( format(prefix + messageGroup.getMessages().get(counter)) );

    }

    @Override
    public void addRepeatingTask(Runnable runnable, Long delay, Long repeating) {
        loader.getServer().getScheduler().runTaskTimer(loader, runnable, delay, repeating);
    }

    @Override
    public void stopAllTasks() {
        loader.getServer().getScheduler().cancelTasks(loader);
    }

    @Override
    public void addCommand(String name, String help, CommandManager manager) {
        loader.getCommand(name).setExecutor( (BukkitCommandManager) manager);
    }

    private String format(String string) {

        string = string.replace("{{AQUA}}", ChatColor.AQUA.toString() );
        string = string.replace("{{BLACK}}", ChatColor.BLACK.toString() );
        string = string.replace("{{BLUE}}", ChatColor.BLUE.toString() );
        string = string.replace("{{BOLD}}", ChatColor.BOLD.toString() );
        string = string.replace("{{DARK_AQUA}}", ChatColor.DARK_AQUA.toString() );
        string = string.replace("{{DARK_BLUE}}", ChatColor.DARK_BLUE.toString() );
        string = string.replace("{{DARK_GRAY}}", ChatColor.DARK_GRAY.toString() );
        string = string.replace("{{DARK_GREEN}}", ChatColor.DARK_GREEN.toString() );
        string = string.replace("{{DARK_PURPLE}}", ChatColor.DARK_PURPLE.toString() );
        string = string.replace("{{DARK_RED}}", ChatColor.DARK_RED.toString() );
        string = string.replace("{{GOLD}}", ChatColor.GOLD.toString() );
        string = string.replace("{{GRAY}}", ChatColor.GRAY.toString() );
        string = string.replace("{{GREEN}}", ChatColor.GREEN.toString() );
        string = string.replace("{{ITALIC}}", ChatColor.ITALIC.toString() );
        string = string.replace("{{LIGHT_PURPLE}}", ChatColor.LIGHT_PURPLE.toString() );
        string = string.replace("{{RED}}", ChatColor.RED.toString() );
        string = string.replace("{{RESET}}", ChatColor.RESET.toString() );
        string = string.replace("{{STRIKETHROUGH}}", ChatColor.STRIKETHROUGH.toString() );
        string = string.replace("{{UNDERLINE}}", ChatColor.UNDERLINE.toString() );
        string = string.replace("{{WHITE}}", ChatColor.WHITE.toString() );
        string = string.replace("{{YELLOW}}", ChatColor.YELLOW.toString() );

        return string;
    }

}
