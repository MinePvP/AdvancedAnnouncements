package ch.minepvp.announcer.command.manager.spout;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.command.manager.CommandManager;
import org.spout.api.command.Command;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandExecutor;
import org.spout.api.command.CommandSource;
import org.spout.api.exception.CommandException;

public class SpoutCommandManager implements CommandManager, CommandExecutor {

    @Override
    public void processCommand(CommandSource commandSource, Command command, CommandContext args) throws CommandException {

        if (Announcer.getInstance().commandExist(command.getPreferredName())) {

            String[] newargs;

            if (args.length() == 0) {
                newargs = new String[0];
            } else {
                newargs = new String[args.length() - 1];
                for (int i = 1; i <= newargs.length; i++) {
                    newargs[i - 1] = args.getString(i);
                }
            }

            if (args.length() == 0) {
                Announcer.getInstance().getCommandHandler( command.getPreferredName() )
                        .execute(commandSource.getName(), "", newargs);
            } else {
                Announcer.getInstance().getCommandHandler(command.getPreferredName())
                        .execute(commandSource.getName(), args.getString(0), newargs);
            }

        }

    }

}
