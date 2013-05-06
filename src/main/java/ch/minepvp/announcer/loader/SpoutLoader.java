package ch.minepvp.announcer.loader;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.ServerType;
import org.spout.api.plugin.CommonPlugin;

public class SpoutLoader extends CommonPlugin implements Loader {

    @Override
    public void onEnable() {
        new Announcer(this, getLogger());

        getLogger().info("enabled");
    }

    @Override
    public void onDisable() {
        Announcer.getInstance().disable();

        getLogger().info("disabled");
    }

    @Override
    public ServerType getServerType() {
        return ServerType.SPOUT;
    }

}
