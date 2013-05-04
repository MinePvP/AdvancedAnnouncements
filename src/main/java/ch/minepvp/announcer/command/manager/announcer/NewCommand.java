package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class NewCommand extends AnnouncerCommand{

    @Override
    public void execute(String sender, String[] args) {
        messageGroupManager.createMessageGroup();
        sendMessage(sender, "{{GOLD}}New MessageGroup created");
    }

    @Override
    public String help() {
        return "{{RED}}/announcer new";
    }

    @Override
    public int maxArgs() {
        return 0;
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public boolean cliSupport() {
        return false;
    }

    @Override
    public String getPermissionNode() {
        return "announcer.command.new";
    }

}
