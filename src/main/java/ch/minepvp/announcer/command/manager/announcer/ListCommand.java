package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;
import ch.minepvp.announcer.messagegroup.MessageGroup;

public class ListCommand extends AnnouncerCommand {

    @Override
    public void execute( String sender, String[] args ) {

        sendMessage(sender, "{{BLUE}}-----------------------------------------------------");
        sendMessage(sender, "{{WHITE}}MessageGroups");
        sendMessage(sender, "{{BLUE}}-----------------------------------------------------");

        int i = 0;

        for ( MessageGroup messageGroup : messageGroupManager.getMessageGroups() ) {

            sendMessage(sender, "{{BLUE}}ID : {{YELLOW}}" + i);
            sendMessage(sender, "{{BLUE}}  Worlds");

            int j = 0;

            for ( String world : messageGroup.getWorlds() ) {
                sendMessage(sender, "     {{YELLOW}}" + j + "{{WHITE}} - {{YELLOW}}" + world);
                j++;
            }

            sendMessage(sender, "{{BLUE}}  Permission : {{YELLOW}}" + messageGroup.getPermission());
            sendMessage(sender, "{{BLUE}}  Settings :");
            sendMessage(sender, "{{BLUE}}    Interval : {{YELLOW}}" + messageGroup.getInterval());
            sendMessage(sender, "{{BLUE}}    Random : {{YELLOW}}" + messageGroup.getRandom());
            sendMessage(sender, "{{BLUE}}    Prefix : {{YELLOW}}" + messageGroup.getPrefix());


            sendMessage(sender, "{{BLUE}}  Messages");

            j = 0;

            for ( String message : messageGroup.getMessages() ) {
                sendMessage(sender, "     {{YELLOW}}" + j + "{{WHITE}} - {{YELLOW}}" + message);
                j++;
            }

            sendMessage(sender, "{{BLUE}}-----------------------------------------------------");

        }

    }

    @Override
    public String help() {
        return "{{WHITE}}/announcer list";
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
        return "announcer.command.list";
    }

}
