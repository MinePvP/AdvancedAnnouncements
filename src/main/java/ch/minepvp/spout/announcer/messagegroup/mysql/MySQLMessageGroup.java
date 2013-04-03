package ch.minepvp.spout.announcer.messagegroup.mysql;

import ch.minepvp.spout.announcer.messagegroup.MessageGroup;
import com.alta189.simplesave.Database;
import com.alta189.simplesave.Field;
import com.alta189.simplesave.Id;
import com.alta189.simplesave.Table;

import java.util.ArrayList;

@Table("MessageGroup")
public class MySQLMessageGroup implements MessageGroup {

    @Id
    private int id;

    @Field
    private String permission = "";

    @Field
    private String chatChannel = "";

    @Field
    private String local = "";


    // Settings
    @Field
    private String prefix = "";

    @Field
    private Long interval = 0L;

    @Field
    private Boolean random = false;

    private ArrayList<MySQLMessage> mySQLMessages;
    private ArrayList<MySQLWorld> mySQLWorlds;


    public ArrayList<String> getWorlds() {

        ArrayList<String> worlds = new ArrayList<String>();

        if ( mySQLWorlds.size() > 0 ) {

            for ( MySQLWorld world : mySQLWorlds ) {
                worlds.add( world.getWorld() );
            }

        }

        return worlds;
    }

    public void addWorld(String world) {

        MySQLWorld mySQLWorld = new MySQLWorld();
        mySQLWorld.setWorld(world);
        mySQLWorld.setGroup(id);

    }

    public void removeWorld(Integer id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Boolean getRandom() {
        return random;
    }

    public void setRandom(Boolean random) {
        this.random = random;
    }

    public ArrayList<String> getMessages() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeMessage(Integer id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addMessage(String message) {

        MySQLMessage mySQLMessage = new MySQLMessage();
        mySQLMessage.setMessage(message);
        mySQLMessage.setGroup(id);

    }


    public String getChatChannel() {
        return chatChannel;
    }

    public void setChatChannel(String chatChannel) {
        this.chatChannel = chatChannel;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }




    // Intern Stuff
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<MySQLMessage> getMySQLMessages() {
        return mySQLMessages;
    }

    public void setMySQLMessages(ArrayList<MySQLMessage> mySQLMessages) {
        this.mySQLMessages = mySQLMessages;
    }

    public ArrayList<MySQLWorld> getMySQLWorlds() {
        return mySQLWorlds;
    }

    public void setMySQLWorlds(ArrayList<MySQLWorld> mySQLWorlds) {
        this.mySQLWorlds = mySQLWorlds;
    }

}
