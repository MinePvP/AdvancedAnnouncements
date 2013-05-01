package ch.minepvp.announcer.manager.mysql;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.manager.MessageGroupManager;
import ch.minepvp.announcer.messagegroup.MessageGroup;
import ch.minepvp.announcer.messagegroup.mysql.MySQLMessage;
import ch.minepvp.announcer.messagegroup.mysql.MySQLMessageGroup;
import ch.minepvp.announcer.messagegroup.mysql.MySQLWorld;
import ch.minepvp.announcer.task.AnnouncerGroupTask;
import com.alta189.simplesave.Database;
import com.alta189.simplesave.DatabaseFactory;
import com.alta189.simplesave.exceptions.ConnectionException;
import com.alta189.simplesave.exceptions.TableRegistrationException;
import com.alta189.simplesave.mysql.MySQLConfiguration;

import java.util.ArrayList;

public class MySQLMessageGroupManager implements MessageGroupManager {

    private Announcer plugin;

    private Database db;

    private ArrayList<MessageGroup> messageGroups;


    public MySQLMessageGroupManager() {
        plugin = Announcer.getInstance();

        // MySQL Connection
        MySQLConfiguration mysql = new MySQLConfiguration();
        mysql.setHost(plugin.getConfig().getString("Settings.MySQL.Hostname"));
        mysql.setPort( plugin.getConfig().getInt("Settings.MySQL.Port") );
        mysql.setDatabase(plugin.getConfig().getString("Settings.MySQL.Database"));
        mysql.setUser( plugin.getConfig().getString("Settings.MySQL.Username") );
        mysql.setPassword(plugin.getConfig().getString("Settings.MySQL.Password"));

        db = DatabaseFactory.createNewDatabase(mysql);

        try {
            db.registerTable(MySQLMessageGroup.class);
            db.registerTable(MySQLMessage.class);
            db.registerTable(MySQLWorld.class);
        } catch (TableRegistrationException e) {
            e.printStackTrace();
        }

        try {
            db.connect();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }

        load();

        // DEBUG
        plugin.getLogger().info( "MessageGroups loaded from MySQL" + messageGroups.size() );

        startTasks();

    }

    public void load() {

        messageGroups = (ArrayList)db.select(MySQLMessageGroup.class).execute().find();

        if ( messageGroups.size() > 0 ) {

            for ( MessageGroup messageGroup : messageGroups ) {

                // Load Worlds
                ArrayList<MySQLWorld> mySqlWorlds = (ArrayList)db.select(MySQLWorld.class).where().equal("group", ((MySQLMessageGroup) messageGroup).getId()).execute().find();

                if ( mySqlWorlds.size() > 0 ) {

                    for ( MySQLWorld world : mySqlWorlds ) {
                        messageGroup.addWorld( world.getWorld() );
                    }

                }

                messageGroup.setPermission( messageGroup.getPermission() );
                messageGroup.setChatChannel( messageGroup.getChatChannel() );
                messageGroup.setLocal( messageGroup.getLocal() );

                messageGroup.setPrefix( messageGroup.getPrefix() );
                messageGroup.setInterval( messageGroup.getInterval() );
                messageGroup.setRandom( messageGroup.getRandom() );

                // Load Messages
                ArrayList<MySQLMessage> mySqlMessages = (ArrayList)db.select(MySQLMessage.class).where().equal("group", ((MySQLMessageGroup) messageGroup).getId()).execute().find();

                if ( mySqlMessages.size() > 0 ) {

                    for ( MySQLMessage mySQLMessage : mySqlMessages ) {
                        messageGroup.addMessage(mySQLMessage.getMessage());
                    }

                }

                messageGroups.add(messageGroup);

            }

        }



    }

    public void save() {

        if ( messageGroups.size() > 0 ) {

            for ( MessageGroup messageGroup : messageGroups ) {

                // Save Worlds


                // Save Messages

            }


        }


    }

    private void loadDefaultGroups() {

        // Sample MessageGroups
        MessageGroup messageGroup = new MySQLMessageGroup();





    }



        public void createMessageGroup() {

        MessageGroup messageGroup = new MySQLMessageGroup();
        messageGroups.add(messageGroup);

    }

    public void removeMessageGroup( MessageGroup messageGroup ) {
        messageGroups.remove(messageGroup);
    }

    /**
     * Start the SpoutLoader Tasks
     */
    private void startTasks() {

        for ( MessageGroup messageGroup : messageGroups ) {

            Long interval = plugin.getConfig().getLong("Settings.Interval") * 60;

            if ( messageGroup.getInterval() > 0 ) {
                interval = messageGroup.getInterval() * 60;
            }

            plugin.getCaller().addRepeatingTask(new AnnouncerGroupTask(messageGroup), interval * 20, interval * 20);
        }

    }

    /**
     * Stop all SpoutLoader Tasks
     */
    public void stopTasks() {
        plugin.getCaller().stopAllTasks();
    }

    /**
     * Restart the SpoutLoader Tasks
     */
    public void restartTasks() {
        stopTasks();
        startTasks();
    }

    /**
     * Get all MessageGroups
     *
     * @return ArrayList<MessageGroup> MessageGroup List
     */
    public ArrayList<MessageGroup> getMessageGroups() {
        return messageGroups;
    }

}
