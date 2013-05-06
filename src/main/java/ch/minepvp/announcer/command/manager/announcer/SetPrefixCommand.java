package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class SetPrefixCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, String[] args) {

        if ( messageGroupManager.getMessageGroups().get( Integer.parseInt(args[0]) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        String string = "";

        for ( int i = 1; i < args.length; i++ ) {
            string = string + " " + args[i];
        }

        string = string.trim();

        messageGroupManager.getMessageGroups().get( Integer.parseInt(args[0]) ).setPrefix( string );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}Prefix set");

    }

    @Override
    public String help() {
        return "{{RED}}/announcer setprefix <MessageGroup ID> NEWPrefix :";
    }

    @Override
    public int maxArgs() {
        return 20;
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
        return "announcer.command.setprefix";
    }

}
