package ch.minepvp.announcer.command;

import ch.minepvp.announcer.Announcer;

public abstract class AnnouncerCommand {

    public abstract void execute(String sender, String[] args);

    public boolean permission(String sender) {
        boolean result = true;
        if (getPermissionNode() != null) {
            result = Announcer.getInstance().getCaller().hasPermission(sender, getPermissionNode());
        }
        return result;
    }

    public abstract String help();

    public abstract int maxArgs();

    public abstract int minArgs();

    public abstract boolean playerOnly();

    public abstract String getPermissionNode();

}
