package ch.minepvp.announcer.messagegroup.yaml;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.messagegroup.MessageGroup;

import java.util.ArrayList;

public class YamlMessageGroup implements MessageGroup {

    // Who get the Message?
    private ArrayList<String> worlds = new ArrayList<String>();
    private String permission = "";
    private String chatChannel = "";
    private String local = "";

    // Settings
    private String prefix = "";
    private Long interval = 0L;
    private Boolean random = false;

    private ArrayList<String> messages = new ArrayList<String>();

    public ArrayList<String> getWorlds() {
        return worlds;
    }

    public void addWorld( String world ) {
        this.worlds.add(world);
    }

    public void removeWorld( Integer id ) {
        this.worlds.remove( this.worlds.get(id) );
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
        return messages;
    }

    public void removeMessage(Integer id) {
        this.messages.remove( this.messages.get(id) );
    }

    public void addMessage(String message) {
        this.messages.add(message);
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

}
