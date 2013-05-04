package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class SetPermissionCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, String[] args) {

        if ( messageGroupManager.getMessageGroups().get( Integer.getInteger(args[0]) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        messageGroupManager.getMessageGroups().get( Integer.getInteger(args[0]) ).setPermission( args[1] );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}Permission set");

    }

    @Override
    public String help() {
        return "{{RED}}/announcer setpermission <MessageGroup ID> <Permission>";
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
        return "announcer.command.setpermission";
    }

}
