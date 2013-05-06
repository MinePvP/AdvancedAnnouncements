package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;
import ch.minepvp.announcer.command.CommandArgs;

public class RemoveWorldCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, CommandArgs args) {

        if ( messageGroupManager.getMessageGroups().get( args.getInteger(0) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        if ( messageGroupManager.getMessageGroups().get( args.getInteger(0) ).getWorlds().get( args.getInteger(1) ) == null ) {
            sendMessage(sender, "{{RED}}The World was not found!");
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).removeWorld( args.getInteger(1) );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}World deleted");
    }

    @Override
    public String help() {
        return "{{RED}}/announcer removeworld <MessageGroup ID> <World ID>";
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
        return "announcer.command.removeworld";
    }

}
