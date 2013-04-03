package ch.minepvp.spout.announcer;

import ch.minepvp.spout.announcer.manager.mysql.MySQLMessageGroupManager;
import ch.minepvp.spout.announcer.manager.MessageGroupManager;
import ch.minepvp.spout.announcer.command.AnnouncerCommand;
import ch.minepvp.spout.announcer.config.AnnouncerConfig;
import ch.minepvp.spout.announcer.manager.yaml.YamlMessageGroupManager;
import ch.minepvp.spout.announcer.messagegroup.MessageGroup;
import ch.minepvp.spout.announcer.metrics.Metrics;
import org.spout.api.Server;
import org.spout.api.UnsafeMethod;
import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.plugin.CommonPlugin;

import java.util.logging.Level;

public class Announcer extends CommonPlugin {

	private static Announcer instance;
    private Server server;

	private AnnouncerConfig config;

	private MessageGroupManager messageGroupManager;

    private Metrics metrics;

	@Override
	public void onLoad() {
		instance = this;
		config = new AnnouncerConfig(getDataFolder());
	}

	@Override
	@UnsafeMethod
	public void onEnable() {

		try {
			config.load();
		} catch (ConfigurationException e) {
			getLogger().log(Level.WARNING, "Error loading Announcer configuration: ", e);
		}

        server = (Server) getEngine();

        // Load Message Groups from Yaml ore MySQL
        if ( AnnouncerConfig.MYSQL.getBoolean() ) {
            messageGroupManager = new MySQLMessageGroupManager();
        } else {
            messageGroupManager = new YamlMessageGroupManager();
        }

        // Load Metrics when Activated
        if ( AnnouncerConfig.METRICS.getBoolean() ) {

            try {
                metrics  = new Metrics(this);

                // Construct a graph, which can be immediately used and considered as valid
                Metrics.Graph graph = metrics.createGraph("Percentage of Database usage");

                // MySQL
                graph.addPlotter(new Metrics.Plotter("MySQL") {

                    @Override
                    public int getValue() {

                        if ( messageGroupManager instanceof MySQLMessageGroupManager ) {
                            return 1;
                        }

                        return 0;
                    }

                });

                // Yaml
                graph.addPlotter(new Metrics.Plotter("Yaml") {

                    @Override
                    public int getValue() {

                        if ( messageGroupManager instanceof YamlMessageGroupManager ) {
                            return 1;
                        }

                        return 0;
                    }

                });

                // Amount of MessageGroups
                metrics.addCustomData(new Metrics.Plotter("Total MessageGroups") {

                    @Override
                    public int getValue() {
                        return messageGroupManager.getMessageGroups().size();
                    }

                });

                // Amount of Messages
                metrics.addCustomData(new Metrics.Plotter("Total Messages") {

                    @Override
                    public int getValue() {

                        int counter = 0;

                        if ( messageGroupManager.getMessageGroups().size() > 0 ) {
                            for ( MessageGroup messageGroup : messageGroupManager.getMessageGroups() ) {
                                counter += messageGroup.getMessages().size();
                            }
                        }

                        return counter;
                    }

                });

                metrics.start();
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }

		//Register commands
		CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(getEngine(), new SimpleInjector(this), new SimpleAnnotatedCommandExecutorFactory());
		getEngine().getRootCommand().addSubCommands(this, AnnouncerCommand.class, commandRegFactory);

		getLogger().info("enabled");
	}

	@Override
	@UnsafeMethod
	public void onDisable() {

        messageGroupManager.stopTasks();
        messageGroupManager.save();

		getLogger().info("disabled");
	}

	public static Announcer getInstance() {
		return instance;
	}

    public Server getServer() {
        return server;
    }

	public AnnouncerConfig getConfig() {
		return config;
	}

	public MessageGroupManager getMessageGroupManager() {
		return messageGroupManager;
	}

    public Metrics getMetrics() {
        return metrics;
    }

}
