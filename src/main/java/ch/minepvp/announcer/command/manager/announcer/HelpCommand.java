package ch.minepvp.announcer.command.manager.announcer;

import ch.minepvp.announcer.command.AnnouncerCommand;

public class HelpCommand extends AnnouncerCommand {

    @Override
    public void execute(String sender, String[] args) {

        sendMessage(sender, "{{BLUE}}-----------------------------------------------------");
        sendMessage(sender, "{{YELLOW}}Help");
        sendMessage(sender, "{{BLUE}}-----------------------------------------------------");

        if ( hasPermission(sender, "announcer.command.list")  ) {
            sendMessage(sender, "{{WHITE}}/announcer list");
            sendMessage(sender, "{{GRAY}}List all MessageGroups");
        }

        // MessageGroups
        if ( hasPermission(sender, "announcer.command.new")  ) {
            sendMessage(sender, "{{WHITE}}/announcer new");
            sendMessage(sender, "{{GRAY}}Create a new MessageGroup");
        }

        if ( hasPermission(sender, "announcer.command.delete")  ) {
            sendMessage(sender, "{{WHITE}}/announcer delete <MessageGroup ID>");
            sendMessage(sender, "{{GRAY}}Delete a MessageGroup");
        }

        // Messages
        if ( hasPermission(sender, "announcer.command.addmessage")  ) {
            sendMessage(sender, "{{WHITE}}/announcer addmessage <MessageGroup ID> New Message");
            sendMessage(sender, "{{GRAY}}Add a Message");
        }

        if ( hasPermission(sender, "announcer.command.removemessage")  ) {
            sendMessage(sender, "{{WHITE}}/announcer removemessage <MessageGroup ID> New Message");
            sendMessage(sender, "{{GRAY}}Remove a Message");
        }

        // World
        if ( hasPermission(sender, "announcer.command.addworld")  ) {
            sendMessage(sender, "{{WHITE}}/announcer addworld <MessageGroup ID> <world_name>");
            sendMessage(sender, "{{GRAY}}Add a World");
        }

        if ( hasPermission(sender, "announcer.command.removeworld")  ) {
            sendMessage(sender, "{{WHITE}}/announcer removeworld <MessageGroup ID> <id>");
            sendMessage(sender, "{{GRAY}}Remove a World");
        }

        // Permission
        if ( hasPermission(sender, "announcer.command.setpermission")  ) {
            sendMessage(sender, "{{WHITE}}/announcer setpermission <MessageGroup ID> <permission>");
            sendMessage(sender, "{{GRAY}}Set the Permission");
        }

        // Random
        if ( hasPermission(sender, "announcer.command.setrandom")  ) {
            sendMessage(sender, "{{WHITE}}/announcer setrandom <MessageGroup ID> <true|false>");
            sendMessage(sender, "{{GRAY}}Set the Message output Random or in order");
        }

        // Prefix
        if ( hasPermission(sender, "announcer.command.setprefix")  ) {
            sendMessage(sender, "{{WHITE}}/announcer setprefix <MessageGroup ID> NewPrefix :");
            sendMessage(sender, "{{GRAY}}Set the Prefix for this MessageGroup");
        }

        // Interval
        if ( hasPermission(sender, "announcer.command.setinterval")  ) {
            sendMessage(sender, "{{WHITE}}/announcer setinterval <MessageGroup ID> <Minutes>");
            sendMessage(sender, "{{GRAY}}Set the Interval for this MessageGroup");
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
