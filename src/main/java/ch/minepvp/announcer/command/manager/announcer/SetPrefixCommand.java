package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;
import ch.minepvp.announcer.command.CommandArgs;

public class SetPrefixCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, CommandArgs args) {

        if ( messageGroupManager.getMessageGroups().get( args.getInteger(0) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        String string = "";

        for ( int i = 1; i < args.getArguments().size(); i++ ) {
            string = string + " " + args.getString(i);
        }

        string = string.trim();

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).setPrefix( string );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}Prefix set");

    }

    @Override
    public String help() {
        return "<MessageGroup ID> NEWPrefix :";
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
        return "announcer.set.prefix";
    }

}
