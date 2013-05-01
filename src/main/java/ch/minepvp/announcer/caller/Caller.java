package ch.minepvp.announcer.caller;

import ch.minepvp.announcer.loader.Loader;
import ch.minepvp.announcer.messagegroup.MessageGroup;

import java.io.File;

public interface Caller {

    public Loader getLoader();

    public File getDataFolder();

    public String getFullServerVersion();

    public Integer getOnlinePlayers();

    public boolean hasPermission( String name, String permission );

    public void sendMessage( String name, String message );

    public void sendAnnouncment( MessageGroup messageGroup, String prefix, Integer counter);

    public void addRepeatingTask( Runnable runnable, Long delay, Long repeating );

    public void stopAllTasks();

}
