package ch.minepvp.announcer.manager.yaml;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.config.Config;
import ch.minepvp.announcer.manager.MessageGroupManager;
import ch.minepvp.announcer.messagegroup.MessageGroup;
import ch.minepvp.announcer.messagegroup.yaml.YamlMessageGroup;
import ch.minepvp.announcer.task.AnnouncerGroupTask;

import java.util.ArrayList;

public class YamlMessageGroupManager implements MessageGroupManager {

    private Announcer plugin;
    private Config config;

    private ArrayList<MessageGroup> messageGroups = new ArrayList<MessageGroup>();

    public YamlMessageGroupManager() {
        plugin = Announcer.getInstance();
        config = plugin.getConfig();

        load();

        // DEBUG
        plugin.getLogger().info( "MessageGroups loaded from Yaml : " + messageGroups.size() );

        startTasks();
    }

    /**
     * Load all MessageGroups
     */
    public void load() {

        if ( config.has("MessageGroups") ) {

            // Get all Groups
            for ( int i = 0; i < plugin.getConfig().getChildrenSize("MessageGroups"); i++ ) {

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
        config.setValue("MessageGroups", "");

        for ( int i = 0; i < messageGroups.size(); i++ ) {
            saveGroup(i);
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
        if ( config.getChildrenSize("MessageGroups." + id + ".Worlds") > 0 ) {

            for ( String world : config.getStringList("MessageGroups." + id + ".Worlds") ) {
                messageGroup.addWorld(world);
            }

        }

        // Load Permission
        messageGroup.setPermission( config.getString("MessageGroups." + id + ".Permission") );

        // Load Local
        messageGroup.setLocal( config.getString("MessageGroups." + id + ".Local") );

        // Load ChatChannel
        messageGroup.setChatChannel( config.getString("MessageGroups." + id + ".ChatChannel") );

        // Load Settings
        messageGroup.setInterval(config.getLong("MessageGroups." + id + ".Settings.Interval") );
        messageGroup.setRandom(config.getBoolean("MessageGroups." + id + ".Settings.Random") );
        messageGroup.setPrefix(config.getString("MessageGroups." + id + ".Settings.Prefix") );

        // Load Messages
        if ( config.getChildrenSize("MessageGroups." + id + ".Messages") > 0 ) {

            for ( String message : config.getStringList("MessageGroups." + id + ".Messages") ) {
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
        config.setValue("MessageGroups." + id + ".Worlds", messageGroups.get(id).getWorlds() );

        // Save Permission
        config.setValue("MessageGroups." + id + ".Permission", messageGroups.get(id).getPermission() );

        // Save Local
        config.setValue("MessageGroups." + id + ".Local", messageGroups.get(id).getLocal() );

        // Save ChatChannel
        config.setValue("MessageGroups." + id + ".ChatChannel", messageGroups.get(id).getChatChannel() );

        // Save Settings
        config.setValue("MessageGroups." + id + ".Settings.Interval", messageGroups.get(id).getInterval() );
        config.setValue("MessageGroups." + id + ".Settings.Random", messageGroups.get(id).getRandom() );
        config.setValue("MessageGroups." + id + ".Settings.Prefix", messageGroups.get(id).getPrefix() );

        // Save Messages
        config.setValue("MessageGroups." + id + ".Messages", messageGroups.get(id).getMessages() );

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
     * Start the SpoutLoader Tasks
     */
    private void startTasks() {

        for ( MessageGroup messageGroup : messageGroups ) {

            Long interval = config.getLong("Settings.Interval") * 60;

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
