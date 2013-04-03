package ch.minepvp.spout.announcer.messagegroup;

import java.util.ArrayList;

public interface MessageGroup {

    public ArrayList<String> getWorlds();

    public void addWorld( String world );

    public void removeWorld( Integer id );

    public String getPermission();

    public void setPermission(String permission);

    public String getPrefix();

    public void setPrefix(String prefix);

    public Long getInterval();

    public void setInterval(Long interval);

    public Boolean getRandom();

    public void setRandom(Boolean random);

    public ArrayList<String> getMessages();

    public void removeMessage(Integer id);

    public void addMessage(String message);

    public String getChatChannel();

    public void setChatChannel(String chatChannel);

    public String getLocal();

    public void setLocal(String local);

}
