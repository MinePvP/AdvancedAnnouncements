package ch.minepvp.announcer.task;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.messagegroup.MessageGroup;

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

        if (Announcer.getInstance().getCaller().getOnlinePlayers() == 0 || messageGroup.getMessages().size() == 0 ) {
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
            prefix = Announcer.getInstance().getConfig().getString("Settings.Prefix");
        }

        Announcer.getInstance().getCaller().sendAnnouncment( messageGroup, prefix, counter);

        counter();

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
