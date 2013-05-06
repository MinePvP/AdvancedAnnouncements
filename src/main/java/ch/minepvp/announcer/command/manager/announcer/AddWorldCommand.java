package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;
import ch.minepvp.announcer.command.CommandArgs;

public class AddWorldCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, CommandArgs args) {

        if ( messageGroupManager.getMessageGroups().get( args.getInteger(0) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        if ( !announcer.getCaller().hasWorld( args.getString(1) ) ) {
            sendMessage(sender, "{{RED}}The World was not found!");
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).addWorld( args.getString(1) );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}World added");
    }

    @Override
    public String help() {
        return "{{RED}}/announcer addworld <MessageGroup ID> <world_name>";
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
        return "announcer.command.addworld";
    }

}
