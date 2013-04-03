package ch.minepvp.spout.announcer.command;

import ch.minepvp.spout.announcer.Announcer;
import ch.minepvp.spout.announcer.messagegroup.MessageGroup;
import ch.minepvp.spout.announcer.manager.MessageGroupManager;
import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.CommandPermissions;
import org.spout.api.exception.CommandException;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.lang.Translation;

import java.util.logging.Level;

public class AnnouncerCommands {
	
	private final Announcer plugin;
    private MessageGroupManager messageGroupManager;

	public AnnouncerCommands(Announcer instance) {
		plugin = instance;
        messageGroupManager = plugin.getMessageGroupManager();
	}


    /*
    * Help
    */
    @Command(aliases = {"help"}, usage = "", desc = "List all Commands.")
    @CommandPermissions("announcer.help")
    public void help(CommandContext args, CommandSource source) throws CommandException {

        source.sendMessage( ChatArguments.fromFormatString("{{BLUE}}-----------------------------------------------------") );
        source.sendMessage( ChatArguments.fromFormatString("{{YELLOW}}Help /announcer /ao") );
        source.sendMessage( ChatArguments.fromFormatString("{{BLUE}}-----------------------------------------------------") );

        if ( source.hasPermission("announcer.list") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 list /announcer list <MessageGroup Id>", source, args.getCommand() ) ) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}List all Groups / O", source) ) );
        }

        if ( source.hasPermission("announcer.new") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 new", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Create a new MessageGroup", source) ) );
        }

        if ( source.hasPermission("announcer.delete") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 delete <MessageGroup Id>", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Delete the MessageGroup", source) ) );
        }

        if ( source.hasPermission("announcer.message.add") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 addmessage <MessageGroup Id> <Message>", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Add a new Message", source) ) );
        }

        if ( source.hasPermission("announcer.message.remove") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 removemessage <MessageGroup> <Message id>", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Remove a Message", source) ) );
        }

        if ( source.hasPermission("announcer.world.add") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 addworld <MessageGroup Id> <world>", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Add a World for the MessageGroup", source) ) );
        }

        if ( source.hasPermission("announcer.world.remove") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 removeworld <MessageGroup Id> <World id>", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Remove a World from a MessageGroup", source) ) );
        }

        if ( source.hasPermission("announcer.set.permission") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 setpermission <MessageGroup Id> <Permission>", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Set a Permission for the Message", source) ) );
        }

        if ( source.hasPermission("announcer.set.random") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 setrandom <MessageGroup Id> <true|false>", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Set the Message Output to Random", source) ) );
        }

        if ( source.hasPermission("announcer.set.interval") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 setinterval <MessageGroup Id> <time>", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Set the Message Output Invertal", source) ) );
        }

        if ( source.hasPermission("announcer.reload") ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{YELLOW}}/%0 reload", source, args.getCommand() )) );
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}-> {{WHITE}}Reload the Configuration", source) ) );
        }

        source.sendMessage( ChatStyle.DARK_GREEN, "-----------------------------------------------------" );
    }


    /*
     * List Groups and Messages
     */
    @Command(aliases = {"list"}, usage = "", desc = "List all MessageGroups.")
    @CommandPermissions("announcer.list")
    public void list(CommandContext args, CommandSource source) throws CommandException {

        source.sendMessage( ChatArguments.fromFormatString("{{BLUE}}-----------------------------------------------------") );
        source.sendMessage( ChatArguments.fromFormatString("{{YELLOW}}MessageGroup List") );
        source.sendMessage( ChatArguments.fromFormatString("{{BLUE}}-----------------------------------------------------") );

        int i = 0;

        for ( MessageGroup messageGroup : plugin.getMessageGroupManager().getMessageGroups() ) {

            source.sendMessage( ChatStyle.BLUE, "ID : ", ChatStyle.YELLOW, i );
            source.sendMessage( ChatStyle.BLUE, "  Worlds : ");

            int j = 0;

            for ( String world : messageGroup.getWorlds() ) {
                source.sendMessage( ChatStyle.BLUE, "     ", ChatStyle.YELLOW, j, ChatStyle.WHITE, " - ", world);
                j++;
            }

            source.sendMessage( ChatStyle.BLUE, "  Permission : ", ChatStyle.WHITE, messageGroup.getPermission() );

            source.sendMessage( ChatStyle.BLUE, "  Settings : ");

            source.sendMessage( ChatStyle.BLUE, "     Interval : ", ChatStyle.WHITE, messageGroup.getInterval() );
            source.sendMessage( ChatStyle.BLUE, "     Random : ", ChatStyle.WHITE, messageGroup.getRandom() );
            source.sendMessage( ChatStyle.BLUE, "     Prefix : ", ChatStyle.WHITE, ChatArguments.fromFormatString( messageGroup.getPrefix() ) );

            source.sendMessage( ChatStyle.BLUE, "  Message : ");

            j = 0;

            for ( String message : messageGroup.getMessages() ) {
                source.sendMessage( ChatStyle.BLUE, "     ", ChatStyle.YELLOW, j, ChatStyle.WHITE, " - ", ChatArguments.fromFormatString( message ) );
                j++;
            }

            i++;

            source.sendMessage( ChatStyle.DARK_GREEN, "-----------------------------------------------------" );
        }

    }


    /**
     * New MessageGroup
     */
    @Command(aliases = {"new"}, usage = "/announcer new", desc = "Create a new MessageGroup")
    @CommandPermissions("announcer.new")
    public void newGroup(CommandContext args, CommandSource source) throws CommandException {

        messageGroupManager.createMessageGroup();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}New MessageGroup created", source) ) );
    }


    /**
     * Delete MessageGroup
     */
    @Command(aliases = {"delete"}, usage = "/announcer delete <MessageGroup Id>", desc = "Delete a MessageGroup")
    @CommandPermissions("announcer.delete")
    public void deleteGroup(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        messageGroupManager.removeMessageGroup( messageGroupManager.getMessageGroups().get( args.getInteger(0) ) );
        messageGroupManager.save();

        source.sendMessage(ChatArguments.fromFormatString(Translation.tr("{{GOLD}}MessageGroup deleted", source)));
    }


    /**
     * Add Message
     */
    @Command(aliases = {"addmessage", "addmsg"}, usage = "/announcer addmessage <MessageGroup Id> This is a {{RED}}new {{WHITE}}Message with ColorCodes", desc = "Add a Message")
    @CommandPermissions("announcer.message.add")
    public void addmessage(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        String string = "";

        for ( int i = 1; i < args.length(); i++ ) {

            string = string + " " + args.getString(i);

        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).getMessages().add(string);
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}Message added", source) ) );
    }


    /**
     * Remove Message
     */
    @Command(aliases = {"removemessage", "rmmsg"}, usage = "/announcer removemessage <MessageGroup Id> <Message Id>", desc = "Remove a Message")
    @CommandPermissions("announcer.message.remove")
    public void removemessage(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ).getMessages().get( args.getInteger(1) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The Message was not found!", source) ) );
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).removeMessage( args.getInteger(1) );
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}Message removed", source) ) );
    }


    /**
     * Add Worlds
     */
    @Command(aliases = {"addworld"}, usage = "/announcer addworld <MessageGroup Id> world_nether", desc = "Add a World")
    @CommandPermissions("announcer.world.add")
    public void addWorld(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        if ( plugin.getServer().getWorld( args.getString(1) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The World was not found!", source) ) );
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).getWorlds().add( args.getString(1) );
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}World added", source) ) );
    }


    /**
     * Remove Worlds
     */
    @Command(aliases = {"removeworld", "rmworld"}, usage = "/announcer removeworld <World Id>", desc = "Remove a World")
    @CommandPermissions("announcer.world.remove")
    public void removeWorld(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        if ( messageGroupManager.getMessageGroups().get( args.getInteger(0) ).getWorlds().get( args.getInteger(1) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The World was not found!", source) ) );
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).removeWorld( args.getInteger(1) );
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}World removed", source) ) );
    }


    /**
     * Set Permission
     */
    @Command(aliases = {"setpermission"}, usage = "/announcer setpermission <MessageGroup Id> <Permission>", desc = "Set Permission who is need to Revice the Messages")
    @CommandPermissions("announcer.set.permission")
    public void setPermission(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).setPermission( args.getString(1) );
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}Permission set to {{YELLOW}}%0", source, args.getString(1)) ) );
    }

    /*
     * Set Random
     */
    @Command(aliases = {"setrandom"}, usage = "/announcer setrandom <MessageGroup Id> <true|false>", desc = "Set Random Mode")
    @CommandPermissions("announcer.set.random")
    public void setRandom(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).setRandom( Boolean.parseBoolean( args.getString(1) ) );
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}Random set to {{YELLOW}}%0", source, args.getString(1)) ) );
    }

    /**
     * Set ChatChannel
     */
    @Command(aliases = {"setchatchannel"}, usage = "/announcer setpermission <MessageGroup Id> <Permission>", desc = "Set Permission who is need to Revice the Messages")
    @CommandPermissions("announcer.set.permission")
    public void setChatChannel(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).setChatChannel( args.getString(1) );
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}ChatChannel set to {{YELLOW}}%0", source, args.getString(1)) ) );
    }


    /**
     * Set Prefix
     */
    @Command(aliases = {"setprefix"}, usage = "/announcer setprefix {{RED}}Announcer {{WHITE}}: ", desc = "Set the Prefix")
    @CommandPermissions("announcer.set.prefix")
    public void setPrefix(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        String string = "";

        for ( int i = 1; i < args.length(); i++ ) {

            string = string + " " + args.getString(i);

        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).setPrefix( string );
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}Prefix set to {{WHITE}}: %0", source, string) ) );
    }

    /**
     * Set Local
     */
    @Command(aliases = {"setlocal"}, usage = "/announcer setlocal de_DE", desc = "Set the Local")
    @CommandPermissions("announcer.set.prefix")
    public void setLocal(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).setLocal( args.getString(1) );
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}Local set to {{WHITE}}: %0", source, args.getString(1)) ) );
    }


    /*
     * Set Interval
     */
    @Command(aliases = {"setinterval"}, usage = "/announcer setInterval <MessageGroup Id> <true|false>", desc = "Set Commands Mode")
    @CommandPermissions("announcer.set.interval")
    public void setInterval(CommandContext args, CommandSource source) throws CommandException {

        if ( plugin.getMessageGroupManager().getMessageGroups().get( args.getInteger(0) ) == null ) {
            source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{RED}}The MessageGroup was not found!", source) ) );
            return;
        }

        messageGroupManager.getMessageGroups().get( args.getInteger(0) ).setInterval( Long.parseLong( args.getString(1) ) );
        messageGroupManager.restartTasks();
        messageGroupManager.save();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}Interval set to {{RED}}%0{{GOLD}}sec", source, args.getString(1)) ) );
    }


    /**
     * Reload
     */
    @Command(aliases = {"reload"}, usage = "", desc = "Reload the Configuration.")
    @CommandPermissions("announcer.reload")
    public void reload(CommandContext args, CommandSource source) throws CommandException {
        //load the config again.
        try {
            plugin.getConfig().load();
        } catch (ConfigurationException e) {
            plugin.getLogger().log(Level.WARNING, "Error loading Announcer configuration: ", e);
        }

        plugin.getMessageGroupManager().load();
        plugin.getMessageGroupManager().restartTasks();

        source.sendMessage( ChatArguments.fromFormatString( Translation.tr("{{GOLD}}Configuration reloaded!", source) ) );
    }

}
