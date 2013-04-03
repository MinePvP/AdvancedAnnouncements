package ch.minepvp.spout.announcer.task;

import ch.minepvp.spout.announcer.Announcer;
import ch.minepvp.spout.announcer.messagegroup.MessageGroup;
import ch.minepvp.spout.announcer.config.AnnouncerConfig;
import org.spout.api.chat.ChatArguments;
import org.spout.api.entity.Player;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AnnouncerGroupTask implements Runnable {

    private MessageGroup messageGroup;

    private Integer counter = 0;
    private Integer lastCounter = 999;

    public AnnouncerGroupTask( MessageGroup messageGroup ) {
        this.messageGroup = messageGroup;
    }

    @Override
    public void run() {

        if ( Announcer.getInstance().getServer().getOnlinePlayers().length == 0 || messageGroup.getMessages().size() == 0 ) {
            return;
        }

        if ( messageGroup.getRandom() ) {

            // Generate new Random counter
            random();

        }

        sendMessage();
    }

    /**
     * Send the Announcment to the Players
     */
    private void sendMessage() {

        // Get the right Prefix
        String prefix = null;

        if ( !messageGroup.getPrefix().equalsIgnoreCase("") ) {
            prefix = messageGroup.getPrefix();
        } else {
            prefix = AnnouncerConfig.PREFIX.getString();
        }

        for (Player player : Announcer.getInstance().getServer().getOnlinePlayers() ) {

            Boolean send = false;

            // Is in one of the Worlds?
            if ( messageGroup.getWorlds().size() > 0 ) {

                for ( String world : messageGroup.getWorlds() ) {

                    if ( !world.equalsIgnoreCase("") ) {

                        if ( player.getWorld().getName().equalsIgnoreCase( world ) ) {
                            send = true;
                        }

                    }

                }

            } else {
                send = true;
            }

            // Has the Permission?
            if ( !messageGroup.getPermission().equalsIgnoreCase("") ) {

                send = player.hasPermission( messageGroup.getPermission() );

            } else {
                send = true;
            }

            // ChatChannel
            if ( !messageGroup.getChatChannel().equalsIgnoreCase("") ) {

                // TODO implement sending Announcement only to Players in the ChatChannel

            }

            // Local
            if ( !messageGroup.getLocal().equalsIgnoreCase("") ) {

                if ( player.getPreferredLocale().getCode().equalsIgnoreCase( messageGroup.getLocal() ) ) {
                    send = true;
                }

            }

            // Send the Message
            if ( send ) {

                String[] lines = messageGroup.getMessages().get( counter ).split("<newline>");

                if ( lines.length > 0 ) {

                    for ( String line : lines ) {
                        player.sendMessage( ChatArguments.fromFormatString( prefix + line ) );
                    }

                } else {
                    player.sendMessage( ChatArguments.fromFormatString( prefix + messageGroup.getMessages().get( counter ) ) );
                }

            }

            counter();
        }

    }

    private void random() {

        SecureRandom secRandom = null;

        try {
            secRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if ( secRandom == null ) {
            Announcer.getInstance().getLogger().info("Fehler : SecureRandom");
        }

        int size = messageGroup.getMessages().size();

        while ( counter == lastCounter ) {
            counter = secRandom.nextInt( size -1 );
        }

        lastCounter = counter;

    }

    private void counter() {

        if ( !messageGroup.getRandom() ) {
            counter++;

            if ( counter >= messageGroup.getMessages().size() ) {
                counter = 0;
            }

        }

    }
}
