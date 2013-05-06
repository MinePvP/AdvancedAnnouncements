package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class RemoveWorldCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, String[] args) {

        if ( messageGroupManager.getMessageGroups().get( Integer.parseInt(args[0]) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        if ( messageGroupManager.getMessageGroups().get( Integer.parseInt(args[0]) ).getWorlds().get( Integer.parseInt(args[1]) ) == null ) {
            sendMessage(sender, "{{RED}}The World was not found!");
            return;
        }

        messageGroupManager.getMessageGroups().get( Integer.parseInt(args[0]) ).removeWorld( Integer.parseInt(args[1]) );
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
