package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;
import ch.minepvp.announcer.command.CommandArgs;

public class DeleteCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, CommandArgs args) {

        if ( messageGroupManager.getMessageGroups().get( args.getInteger(0) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        messageGroupManager.removeMessageGroup( messageGroupManager.getMessageGroups().get( args.getInteger(0) ) );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}The MessageGroup deleted");
    }

    @Override
    public String help() {
        return "<MessageGroup ID>";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public boolean cliSupport() {
        return false;
    }

    @Override
    public String getPermissionNode() {
        return "announcer.delete";
    }

}
