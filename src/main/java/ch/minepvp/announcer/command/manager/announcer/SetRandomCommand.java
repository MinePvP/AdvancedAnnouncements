package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class SetRandomCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, String[] args) {

        if ( messageGroupManager.getMessageGroups().get( Integer.parseInt(args[0]) ) == null ) {
            sendMessage(sender, "{{RED}}The MessageGroup was not found!");
            return;
        }

        messageGroupManager.getMessageGroups().get( Integer.parseInt(args[0]) ).setRandom( Boolean.parseBoolean(args[1]) );
        messageGroupManager.save();

        sendMessage(sender, "{{GOLD}}Random set");

    }

    @Override
    public String help() {
        return "{{RED}}/announcer setrandom <true|false>";  //To change body of implemented methods use File | Settings | File Templates.
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
