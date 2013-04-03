package ch.minepvp.spout.announcer.config;

import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

import java.io.File;

public class AnnouncerConfig extends ConfigurationHolderConfiguration {
	
	public static final ConfigurationHolder DEBUG = new ConfigurationHolder(false, "Settings", "Debug");
	
	public static final ConfigurationHolder INTERVAL = new ConfigurationHolder(60, "Settings", "Interval");
	public static final ConfigurationHolder PREFIX = new ConfigurationHolder("{{RED}}Announce {{WHITE}}: ", "Settings", "Prefix");

    public static final ConfigurationHolder MYSQL = new ConfigurationHolder(false, "Settings", "MySQL", "Enabled");
    public static final ConfigurationHolder MySQL_HOST = new ConfigurationHolder("localhost", "Settings", "MySQL", "Hostname");
    public static final ConfigurationHolder MYSQL_PORT = new ConfigurationHolder(3306, "Settings", "MySQL", "Port");
    public static final ConfigurationHolder MYSQL_DB = new ConfigurationHolder("database", "Settings", "MySQL", "Database");
    public static final ConfigurationHolder MYSQL_USER = new ConfigurationHolder("username", "Settings", "MySQL", "Username");
    public static final ConfigurationHolder MYSQL_PASSWORD = new ConfigurationHolder("password", "Settings", "MySQL", "Password");

    public static final ConfigurationHolder METRICS = new ConfigurationHolder(true, "Settings", "Metrics");





    public AnnouncerConfig(File dataFolder) {
		super(new YamlConfiguration(new File(dataFolder, "config.yml")));
	}

	@Override
	public void load() throws ConfigurationException {
		super.load();
		super.save();
	}

}
