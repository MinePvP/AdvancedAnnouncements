package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class SetInterval extends AnnouncerCommand {

    @Override
    public void execute(String sender, String[] args) {

        if ( messageGroupManager.getMessageGroups().get( Integer.getInteger(args[0]) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        messageGroupManager.getMessageGroups().get( Integer.getInteger(args[0]) ).setInterval( Long.getLong(args[1]) );
        messageGroupManager.restartTasks();
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}Interval set");

    }

    @Override
    public String help() {
        return "{{RED}}/announcer setinterval <MessageGroup ID> <Minutes>";
    }

    @Override
    public int maxArgs() {
        return 2;
    }

    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public boolean cliSupport() {
        return false;
    }

    @Override
    public String getPermissionNode() {
        return "announcer.command.setinterval";
    }

}
