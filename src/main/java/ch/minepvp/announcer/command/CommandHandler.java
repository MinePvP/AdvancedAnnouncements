package ch.minepvp.announcer.command;

import ch.minepvp.announcer.Announcer;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private Map<String, AnnouncerCommand> subCommands;

    public CommandHandler( String name, String help ) {
        subCommands = new HashMap<String, AnnouncerCommand>();

        Announcer.getInstance().getCaller().addCommand(name, help, Announcer.getInstance().getCommandManager() );
    }

    public void registerSubCommand( String name, AnnouncerCommand command ) {
        subCommands.put(name, command);
    }

    public void execute( String sender, CommandArgs args ) {


        if ( subCommands.containsKey( args.getSubcommand() ) ) {

            AnnouncerCommand command = subCommands.get( args.getSubcommand() );

            // Console?
            if ( !command.cliSupport() && sender.equalsIgnoreCase("console") ) {

                Announcer.getInstance().getCaller().sendMessage(null, "{{RED}}Command can't use from Console!");
                return;
            }

            if ( command.hasPermission(sender) ) {

                if (args.getArguments().size() >= command.minArgs() && args.getArguments().size() <= command.maxArgs()) {
                    command.execute(sender, args);
                } else {
                    Announcer.getInstance().getCaller().sendMessage(sender, "{{RED}}How to use the Command: " + command.help());
                }

            }

        } else {
            Announcer.getInstance().getCaller().sendMessage(sender, "{{RED}}/announcer help");
        }

    }

}
