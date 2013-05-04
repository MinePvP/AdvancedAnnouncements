package ch.minepvp.announcer.command.manager.bukkit;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.command.manager.CommandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BukkitCommandManager implements CommandManager, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if ( Announcer.getInstance().commandExist(command.getName()) ) {

            String[] newargs;

            if (args.length == 0) {
                newargs = new String[0];
                args = new String[1];
                args[0] = "";
            } else {
                newargs = new String[args.length - 1];
                System.arraycopy(args, 1, newargs, 0, args.length - 1);
            }

            Announcer.getInstance().getCommandHandler( command.getName() )
                    .execute( commandSender.getName(), args[0], newargs );

            return true;
        }

        return false;
    }

}
