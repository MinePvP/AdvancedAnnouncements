package ch.minepvp.announcer.command;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.manager.MessageGroupManager;

public abstract class AnnouncerCommand {

    protected Announcer announcer = Announcer.getInstance();
    protected MessageGroupManager messageGroupManager = Announcer.getInstance().getMessageGroupManager();


    public abstract void execute(String sender, CommandArgs args);

    public boolean hasPermission(String sender) {
        boolean result = true;
        if (getPermissionNode() != null) {
            result = Announcer.getInstance().getCaller().hasPermission(sender, getPermissionNode());
        }
        return result;
    }

    public boolean hasPermission(String sender, String permission) {
        return  Announcer.getInstance().getCaller().hasPermission(sender, permission);
    }

    public abstract String help();

    public abstract int maxArgs();

    public abstract int minArgs();

    public abstract boolean cliSupport();

    public abstract String getPermissionNode();

    public void sendMessage( String sender, String message ) {
        Announcer.getInstance().getCaller().sendMessage(sender, message);
    }

}
