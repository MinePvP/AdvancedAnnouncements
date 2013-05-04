package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class RemoveMessageCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, String[] args) {

        if ( messageGroupManager.getMessageGroups().get( Integer.getInteger(args[0]) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        if ( messageGroupManager.getMessageGroups().get( Integer.getInteger(args[0]) ).getMessages().get( Integer.getInteger(args[1]) ) == null ) {
            sendMessage(sender, "{{RED}}The Message was not found!");
            return;
        }

        messageGroupManager.getMessageGroups().get( Integer.getInteger(args[0]) ).removeMessage( Integer.getInteger(args[1]) );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}Message removed");
    }

    @Override
    public String help() {
        return "{{RED}}/announcer removemessage <MesssageGroup ID> <Message ID>";
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
        return "announcer.command.removemessage";
    }

}
