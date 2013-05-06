package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class DeleteCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, String[] args) {

        if ( messageGroupManager.getMessageGroups().get( Integer.parseInt(args[0]) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        messageGroupManager.removeMessageGroup( messageGroupManager.getMessageGroups().get( Integer.parseInt(args[0]) ) );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}The MessageGroup deleted");
    }

    @Override
    public String help() {
        return "{{RED}}/announcer delete <MessageGroup ID>";
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
        return "announcer.command.delete";
    }

}
