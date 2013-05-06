package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;
import ch.minepvp.announcer.command.CommandArgs;
import ch.minepvp.announcer.messagegroup.MessageGroup;

public class ListCommand extends AnnouncerCommand {

    @Override
    public void execute( String sender, CommandArgs args ) {

        sendMessage(sender, "{{BLUE}}-----------------------------------------------------");

        if ( args.getArguments().size() > 0 ) {

            sendMessage(sender, "{{YELLOW}}MessageGroup");
            sendMessage(sender, "{{BLUE}}-----------------------------------------------------");

            listMessageGroup(sender, messageGroupManager.getMessageGroups().get( args.getInteger(0) ), args.getInteger(0));

        } else {

            sendMessage(sender, "{{YELLOW}}MessageGroups");
            sendMessage(sender, "{{BLUE}}-----------------------------------------------------");

            int i = 0;

            for ( MessageGroup messageGroup : messageGroupManager.getMessageGroups() ) {

                listMessageGroup(sender, messageGroup, i);

                i++;
            }

        }

    }

    private void listMessageGroup( String sender, MessageGroup messageGroup, Integer id ) {

        sendMessage(sender, "{{BLUE}}ID : {{YELLOW}}" + id);
        sendMessage(sender, "{{BLUE}}  Worlds");

        int j = 0;

        for ( String world : messageGroup.getWorlds() ) {
            sendMessage(sender, "     {{YELLOW}}" + j + "{{WHITE}} - {{WHITE}}" + world);
            j++;
        }

        sendMessage(sender, "{{BLUE}}  Permission : {{WHITE}}" + messageGroup.getPermission());
        sendMessage(sender, "{{BLUE}}  Settings :");
        sendMessage(sender, "{{BLUE}}    Interval : {{WHITE}}" + messageGroup.getInterval());
        sendMessage(sender, "{{BLUE}}    Random : {{WHITE}}" + messageGroup.getRandom());
        sendMessage(sender, "{{BLUE}}    Prefix : {{WHITE}}" + messageGroup.getPrefix());


        sendMessage(sender, "{{BLUE}}  Messages");

        j = 0;

        for ( String message : messageGroup.getMessages() ) {
            sendMessage(sender, "     {{YELLOW}}" + j + "{{WHITE}} - {{WHITE}}" + message);
            j++;
        }

        sendMessage(sender, "{{BLUE}}-----------------------------------------------------");

    }

    @Override
    public String help() {
        return "<MessageGroup ID>";
    }

    @Override
    public int maxArgs() {
        return 1;
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
        return "announcer.list";
    }

}
