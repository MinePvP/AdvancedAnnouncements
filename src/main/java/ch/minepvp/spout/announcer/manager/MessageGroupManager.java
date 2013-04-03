package ch.minepvp.spout.announcer.manager;

import ch.minepvp.spout.announcer.messagegroup.MessageGroup;

import java.util.ArrayList;

public interface MessageGroupManager {

    public void load();

    public void save();

    public ArrayList<MessageGroup> getMessageGroups();

    public void createMessageGroup();

    public void removeMessageGroup( MessageGroup messageGroup );

    public void stopTasks();

    public void restartTasks();

}
