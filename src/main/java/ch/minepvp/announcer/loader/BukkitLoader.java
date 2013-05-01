package ch.minepvp.announcer.loader;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.ServerType;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitLoader extends JavaPlugin implements Loader {

    @Override
    public void onEnable() {
        new Announcer(this, getLogger());

        //Register commands


        getLogger().info("enabled");
    }

    @Override
    public void onDisable() {

    }

    @Override
    public ServerType getServerType() {
        return ServerType.BUKKIT;
    }

}
