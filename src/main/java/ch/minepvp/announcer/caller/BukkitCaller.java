package ch.minepvp.announcer.caller;

import ch.minepvp.announcer.loader.Loader;
import ch.minepvp.announcer.loader.BukkitLoader;
import ch.minepvp.announcer.messagegroup.MessageGroup;
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
    public void sendMessage(String name, String message) {

        Player player = loader.getServer().getPlayerExact(name);

        // Console
        if ( player == null ) {
            loader.getServer().getConsoleSender().sendMessage( message );
        }

        player.sendMessage( message );

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
                        player.sendMessage( prefix + line );
                    }

                } else {
                    player.sendMessage( prefix + messageGroup.getMessages().get( counter ) );
                }

            }

        }

        // TODO Debug
        loader.getLogger().info( prefix + messageGroup.getMessages().get(counter) );

    }

    @Override
    public void addRepeatingTask(Runnable runnable, Long delay, Long repeating) {
        loader.getServer().getScheduler().runTaskTimer(loader, runnable, delay, repeating);
    }

    @Override
    public void stopAllTasks() {
        loader.getServer().getScheduler().cancelTasks(loader);
    }


}
