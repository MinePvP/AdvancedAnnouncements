package ch.minepvp.announcer.config;

import ch.minepvp.announcer.Announcer;
import ch.minepvp.announcer.loader.SpoutLoader;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

import java.io.File;
import java.util.List;

public class SpoutConfig extends ConfigurationHolderConfiguration implements Config {

    File file;

    public SpoutConfig(File dataFolder) {
		super(new YamlConfiguration(new File(dataFolder, "config.yml")));

        file = new File(dataFolder, "config.yml");

        load();
	}

	@Override
	public void load() {

        try {
            super.load();
        } catch (ConfigurationException e) {
            Announcer.getInstance().getLogger().info("Can't load the Configuration!");
        }

        try {
            super.save();
        } catch (ConfigurationException e) {
            Announcer.getInstance().getLogger().info("Can't save the Configuration!");
        }

    }

    @Override
    public Boolean getBoolean(String path) {
        return getNode(path).getBoolean();
    }

    @Override
    public Integer getInt(String path) {
        return getNode(path).getInt();
    }

    @Override
    public Long getLong(String path) {
        return getNode(path).getLong();
    }

    @Override
    public Double getDouble(String path) {
        return getNode(path).getDouble();
    }

    @Override
    public String getString(String path) {
        return getNode(path).getString();
    }

    @Override
    public List<String> getStringList(String path) {
        return getNode(path).getStringList();
    }

    @Override
    public Integer getChildrenSize(String path) {
        return getNode(path).getChildren().size();
    }

    @Override
    public void setValue(String path, Object value) {
        getNode(path).setValue(value);

        try {
            super.save();
        } catch (ConfigurationException e) {
            Announcer.getInstance().getLogger().info("Can't save the Configuration!");
        }

    }

    @Override
    public Boolean has(String path) {
        return getNode(path).isAttached();
    }

    @Override
    public File getFile() {
        return new File( Announcer.getInstance().getCaller().getDataFolder(), "metrics.yml" );
    }

}
