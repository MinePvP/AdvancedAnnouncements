package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class HelpCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, String[] args) {

        sendMessage(sender, "{{BLUE}}-----------------------------------------------------");
        sendMessage(sender, "{{WHITE}}Help");
        sendMessage(sender, "{{BLUE}}-----------------------------------------------------");

        if ( hasPermission(sender, "announcer.command.list")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer list");
            sendMessage(sender, "{{GOLD}}List all MessageGroups");
        }

        // MessageGroups
        if ( hasPermission(sender, "announcer.command.new")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer new");
            sendMessage(sender, "{{GOLD}}Create a new MessageGroup");
        }

        if ( hasPermission(sender, "announcer.command.delete")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer delete <MessageGroup ID>");
            sendMessage(sender, "{{GOLD}}Delete a MessageGroup");
        }

        // Messages
        if ( hasPermission(sender, "announcer.command.addmessage")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer addmessage <MessageGroup ID> New Message");
            sendMessage(sender, "{{GOLD}}Add a Message");
        }

        if ( hasPermission(sender, "announcer.command.removemessage")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer removemessage <MessageGroup ID> New Message");
            sendMessage(sender, "{{GOLD}}Remove a Message");
        }

        // World
        if ( hasPermission(sender, "announcer.command.addworld")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer addworld <MessageGroup ID> <world_name>");
            sendMessage(sender, "{{GOLD}}Add a World");
        }

        if ( hasPermission(sender, "announcer.command.removeworld")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer removeworld <MessageGroup ID> <id>");
            sendMessage(sender, "{{GOLD}}Remove a World");
        }

        // Permission
        if ( hasPermission(sender, "announcer.command.setpermission")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer setpermission <MessageGroup ID> <permission>");
            sendMessage(sender, "{{GOLD}}Set the Permission");
        }

        // Random
        if ( hasPermission(sender, "announcer.command.setrandom")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer setrandom <MessageGroup ID> <true|false>");
            sendMessage(sender, "{{GOLD}}Set the Message output Random or in order");
        }

        // Prefix
        if ( hasPermission(sender, "announcer.command.setprefix")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer setprefix <MessageGroup ID> NewPrefix :");
            sendMessage(sender, "{{GOLD}}Set the Prefix for this MessageGroup");
        }

        // Interval
        if ( hasPermission(sender, "announcer.command.setinterval")  ) {
            sendMessage(sender, "{{YELLOW}}/announcer setinterval <MessageGroup ID> <Minutes>");
            sendMessage(sender, "{{GOLD}}Set the Interval for this MessageGroup");
        }

        sendMessage(sender, "{{BLUE}}-----------------------------------------------------");
    }

    @Override
    public String help() {
        return "/announcer help";
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
        return true;
    }

    @Override
    public String getPermissionNode() {
        return "announcer.command.help";
    }

}
