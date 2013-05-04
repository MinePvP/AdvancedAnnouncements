package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class AddMessageCommand extends AnnouncerCommand {
    @Override
    public void execute(String sender, String[] args) {

        if ( messageGroupManager.getMessageGroups().get( Integer.getInteger(args[0]) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        String string = "";

        for ( int i = 1; i < args.length; i++ ) {
            string = string + " " + args[i];
        }

        messageGroupManager.getMessageGroups().get( Integer.getInteger(args[0]) ).getMessages().add(string);
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}Message added");
    }

    @Override
    public String help() {
        return "{{RED}}/announcer addmessage <MessageGroup ID> This is a new Message";
    }

    @Override
    public int maxArgs() {
        return 2000;
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
        return "announcer.command.addmessage";
    }
}
