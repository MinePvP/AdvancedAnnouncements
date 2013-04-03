package ch.minepvp.spout.announcer.manager.yaml;

import ch.minepvp.spout.announcer.Announcer;
import ch.minepvp.spout.announcer.config.AnnouncerConfig;
import ch.minepvp.spout.announcer.manager.MessageGroupManager;
import ch.minepvp.spout.announcer.messagegroup.MessageGroup;
import ch.minepvp.spout.announcer.messagegroup.mysql.MySQLMessageGroup;
import ch.minepvp.spout.announcer.messagegroup.yaml.YamlMessageGroup;
import ch.minepvp.spout.announcer.task.AnnouncerGroupTask;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.scheduler.TaskPriority;

import java.util.ArrayList;

public class YamlMessageGroupManager implements MessageGroupManager {

    private Announcer plugin;
    private AnnouncerConfig config;

    private ArrayList<MessageGroup> messageGroups = new ArrayList<MessageGroup>();

    public YamlMessageGroupManager() {
        plugin = Announcer.getInstance();
        config = plugin.getConfig();

        load();

        // DEBUG
        plugin.getLogger().info( "MessageGroups loaded from Yaml" + messageGroups.size() );

        startTasks();
    }

    /**
     * Load all MessageGroups
     */
    public void load() {

        if ( config.getChild("MessageGroups").getChildren().size() > 0 ) {

            // Get all Groups
            for ( int i = 0; i < plugin.getConfig().getChild("MessageGroups").getChildren().size(); i++ ) {

                loadGroup(i);

            }

        } else {
            loadDefaultGroups();
        }

    }

    /**
     * Save all MessageGroups
     */
    public void save() {

        // Override all
        config.getNode("MessageGroups").setValue("");

        for ( int i = 0; i < messageGroups.size(); i++ ) {
            saveGroup(i);
        }

        try {
            config.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Load a MessageGroups
     *
     * @param id YamlMessageGroup ID
     */
    private void loadGroup( Integer id ) {

        MessageGroup messageGroup = new YamlMessageGroup();

        // Load Worlds
        if ( config.getNode("MessageGroups", "" + id, "Worlds").getStringList().size() > 0 ) {

            for ( String world : config.getNode("MessageGroups", "" + id, "Worlds").getStringList() ) {
                messageGroup.addWorld(world);
            }

        }

        // Load Permission
        messageGroup.setPermission( config.getNode("MessageGroups", ""+id, "Permission").getString() );

        // Load Local
        messageGroup.setLocal( config.getNode("MessageGroups", ""+id, "Local").getString() );

        // Load ChatChannel
        messageGroup.setChatChannel( config.getNode("MessageGroups", ""+id, "ChatChannel").getString() );

        // Load Settings
        messageGroup.setInterval(config.getNode("MessageGroups", "" + id, "Settings", "Interval").getLong());
        messageGroup.setRandom(config.getNode("MessageGroups", "" + id, "Settings", "Random").getBoolean());
        messageGroup.setPrefix(config.getNode("MessageGroups", "" + id, "Settings", "Prefix").getString());

        // Load Messages
        if ( config.getNode("MessageGroups", "" + id, "Messages").getStringList().size() > 0 ) {

            for ( String message : config.getNode("MessageGroups", "" + id, "Messages").getStringList() ) {
                messageGroup.addMessage( message );
            }

        }

        messageGroups.add(messageGroup);
    }

    /**
     * Save a YamlMessageGroup
     *
     * @param id YamlMessageGroup ID
     */
    private void saveGroup( Integer id ) {

        // Save Worlds
        config.getNode("MessageGroups", ""+id, "Worlds").setValue( messageGroups.get(id).getWorlds() );

        // Save Permission
        config.getNode("MessageGroups", ""+id, "Permission").setValue( messageGroups.get(id).getPermission() );

        // Save Local
        config.getNode("MessageGroups", ""+id, "Local").setValue( messageGroups.get(id).getLocal() );

        // Save ChatChannel
        config.getNode("MessageGroups", ""+id, "ChatChannel").setValue( messageGroups.get(id).getChatChannel() );

        // Save Settings
        config.getNode("MessageGroups", ""+id, "Settings", "Interval").setValue( messageGroups.get(id).getInterval() );
        config.getNode("MessageGroups", ""+id, "Settings", "Random").setValue( messageGroups.get(id).getRandom() );
        config.getNode("MessageGroups", ""+id, "Settings", "Prefix").setValue( messageGroups.get(id).getPrefix() );

        // Save Messages
        config.getNode("MessageGroups", ""+id, "Messages").setValue( messageGroups.get(id).getMessages() );

    }

    /**
     * Load Default Config
     */
    private void loadDefaultGroups() {

        // Message Group Info
        MessageGroup messageGroup = new YamlMessageGroup();

        messageGroup.addWorld("world");
        messageGroup.addWorld("world_nether");
        messageGroup.addWorld("the_end");

        messageGroup.setPermission("announcer.info");

        messageGroup.setChatChannel("");

        messageGroup.setPrefix("{{YELLOW}}INFO {{WHITE}}: ");

        messageGroup.setRandom(false);
        messageGroup.setInterval(45L);

        messageGroup.addMessage("Server Restarts are at 6am / 12am / 6pm / 12pm");
        messageGroup.addMessage("Read the News on www.minepvp.ath.cx");


        messageGroups.add(messageGroup);

        // Message Group Tips and Triks
        messageGroup = new YamlMessageGroup();

        messageGroup.addWorld("world");
        messageGroup.addWorld("world_nether");

        messageGroup.setPermission("announcer.tips");

        messageGroup.setLocal("de_DE");

        messageGroup.setChatChannel("");

        messageGroup.setPrefix("{{YELLOW}}Tips {{WHITE}}: ");

        messageGroup.setRandom(true);
        messageGroup.setInterval(90L);

        messageGroup.addMessage("Get a new Plot with /plot create");
        messageGroup.addMessage("Get a new City with /city create");

        messageGroups.add(messageGroup);

        // Message Group Rules
        messageGroup = new YamlMessageGroup();

        messageGroup.addWorld("world");
        messageGroup.addWorld("world_nether");
        messageGroup.addWorld("the_end");

        messageGroup.setPermission("announcer.rules");

        messageGroup.setChatChannel("");

        messageGroup.setPrefix("{{YELLOW}}RULES {{WHITE}}: ");

        messageGroup.setRandom(false);
        messageGroup.setInterval(20L);

        messageGroup.addMessage("RULE 1");
        messageGroup.addMessage("RULE 2");
        messageGroup.addMessage("RULE 3");
        messageGroup.addMessage("RULE 4");
        messageGroup.addMessage("RULE 5");
        messageGroup.addMessage("RULE 6");
        messageGroup.addMessage("RULE 7");
        messageGroup.addMessage("RULE 8");
        messageGroup.addMessage("RULE 9");

        messageGroups.add(messageGroup);

        save();
    }

    /**
     *
     */
    public void createMessageGroup() {

        MessageGroup messageGroup = new YamlMessageGroup();
        messageGroups.add(messageGroup);

    }

    /**
     *
     * @param messageGroup
     */
    public void removeMessageGroup( MessageGroup messageGroup ) {
        messageGroups.remove(messageGroup);
    }

    /**
     * Start the Announcer Tasks
     */
    private void startTasks() {

        for ( MessageGroup messageGroup : messageGroups ) {

            Long interval = AnnouncerConfig.INTERVAL.getLong() * 60;

            if ( messageGroup.getInterval() > 0 ) {
                interval = messageGroup.getInterval() * 60;
            }

            plugin.getEngine().getScheduler().scheduleSyncRepeatingTask(plugin, new AnnouncerGroupTask(messageGroup), interval * 20, interval * 20, TaskPriority.NORMAL);
        }

    }

    /**
     * Stop all Announcer Tasks
     */
    public void stopTasks() {
        plugin.getEngine().getScheduler().cancelAllTasks();
    }

    /**
     * Restart the Announcer Tasks
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
