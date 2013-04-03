package ch.minepvp.spout.announcer.command;

import ch.minepvp.spout.announcer.Announcer;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.NestedCommand;
import org.spout.api.exception.CommandException;

public class AnnouncerCommand {
	
	private final Announcer plugin;

	public AnnouncerCommand(Announcer instance) {
		plugin = instance;
	}
	
	@Command(aliases = {"announcer", "ao"}, usage = "", desc = "Access Announcer Commands", min = 1, max = 1)
    @NestedCommand(value = AnnouncerCommands.class) // Subcommands
	public void announcer(CommandContext args, CommandSource source) throws CommandException {
		
	}
	
}
