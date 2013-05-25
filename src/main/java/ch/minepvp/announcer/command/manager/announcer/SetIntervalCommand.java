package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;
import ch.minepvp.announcer.command.CommandArgs;

public class SetIntervalCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, CommandArgs args) {

        if ( messageGroupManager.getMessageGroups().get( args.getInteger(0) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).setInterval( args.getLong(1) );
        messageGroupManager.restartTasks();
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}Interval set");

    }

    @Override
    public String help() {
        return "<MessageGroup ID> <Minutes>";
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
        return "announcer.set.interval";
    }

}
