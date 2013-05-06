package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;
import ch.minepvp.announcer.command.CommandArgs;

public class SetRandomCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, CommandArgs args) {

        if ( messageGroupManager.getMessageGroups().get( args.getInteger(0) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).setRandom( args.getBoolean(1) );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}Random set");

    }

    @Override
    public String help() {
        return "{{RED}}/announcer setrandom <true|false>";
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
        return "announcer.command.setrandom";
    }

}
