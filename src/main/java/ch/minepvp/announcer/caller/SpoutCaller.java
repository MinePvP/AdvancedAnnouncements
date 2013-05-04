package ch.minepvp.announcer.caller;

import ch.minepvp.announcer.command.manager.CommandManager;
import ch.minepvp.announcer.command.manager.spout.SpoutCommandManager;
import ch.minepvp.announcer.loader.Loader;
import ch.minepvp.announcer.loader.SpoutLoader;
import ch.minepvp.announcer.messagegroup.MessageGroup;
import org.spout.api.Server;
import org.spout.api.chat.ChatArguments;
import org.spout.api.entity.Player;
import org.spout.api.scheduler.TaskPriority;

import java.io.File;

public class SpoutCaller implements Caller {

    private SpoutLoader loader;

    public SpoutCaller( Loader loader ) {
        this.loader = (SpoutLoader) loader;
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
        return loader.getEngine().getVersion();
    }

    @Override
    public String getPluginVersion() {
        return loader.getDescription().getVersion();
    }

    @Override
    public Integer getOnlinePlayers() {
        return ((Server) loader.getEngine()).getOnlinePlayers().length;
    }

    @Override
    public boolean hasPermission(String name, String permission) {

        Player player = loader.getEngine().getPlayer(name, true);

        // Console
        if ( player == null ) {
            return true;
        }

        return player.hasPermission( permission );

    }

    @Override
    public boolean hasWorld(String world) {

        if ( loader.getEngine().getWorld(world) != null ) {
            return true;
        }

        return false;
    }

    @Override
    public void sendMessage(String name, String message) {

        Player player = loader.getEngine().getPlayer(name, true);

        // Console
        if ( player == null ) {
            loader.getEngine().getCommandSource().sendMessage(ChatArguments.fromFormatString( message ));
            return;
        }

        player.sendMessage(ChatArguments.fromFormatString(message));

    }

    @Override
    public void sendAnnouncment(MessageGroup messageGroup, String prefix, Integer counter) {

        for (Player player : ((Server)loader.getEngine()).getOnlinePlayers() ) {

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

            // ChatChannel
            if ( !messageGroup.getChatChannel().equalsIgnoreCase("") ) {

                // TODO implement sending Announcement only to Players in the ChatChannel

            }

            // Local
            if ( !messageGroup.getLocal().equalsIgnoreCase("") ) {

                if ( player.getPreferredLocale().getCode().equalsIgnoreCase( messageGroup.getLocal() ) ) {
                    send = true;
                }

            }

            // Send the Message
            if ( send ) {

                String[] lines = messageGroup.getMessages().get( counter ).split("<newline>");

                if ( lines.length > 0 ) {

                    for ( String line : lines ) {
                        player.sendMessage( ChatArguments.fromFormatString( prefix + line ) );
                    }

                } else {
                    player.sendMessage( ChatArguments.fromFormatString( prefix + messageGroup.getMessages().get( counter ) ) );
                }

            }

        }

        // TODO Debug
        loader.getLogger().info( prefix + messageGroup.getMessages().get(counter) );

    }

    @Override
    public void addRepeatingTask(Runnable runnable, Long delay, Long repeating) {
        loader.getEngine().getScheduler().scheduleSyncRepeatingTask(loader, runnable, delay * 60, repeating * 60, TaskPriority.NORMAL);
    }

    @Override
    public void stopAllTasks() {
        loader.getEngine().getScheduler().cancelAllTasks();
    }

    @Override
    public void addCommand(String name, String help, CommandManager manager) {
        loader.getEngine().getRootCommand().addSubCommand(loader, name).setHelp(help).setExecutor((SpoutCommandManager) manager);
    }


}
