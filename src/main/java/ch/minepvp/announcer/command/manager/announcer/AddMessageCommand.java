package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;
import ch.minepvp.announcer.command.CommandArgs;

public class AddMessageCommand extends AnnouncerCommand {
    @Override
    public void execute(String sender, CommandArgs args) {

        if ( messageGroupManager.getMessageGroups().get( args.getInteger(0) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        String string = "";

        for ( int i = 1; i < args.getArguments().size(); i++ ) {
            string = string + " " + args.getArguments().get(i);
        }

        string = string.trim();

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).getMessages().add(string);
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
