package ch.minepvp.announcer.command;

import java.util.ArrayList;
import java.util.List;

public class CommandArgs {

    private String command = "";
    private String subCommand = "";

    private List<String> arguments = new ArrayList<String>();

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getSubCommand() {
        return subCommand;
    }

    public void setSubCommand(String subcommand) {
        this.subCommand = subcommand;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void addArgument( String arg ) {
        this.arguments.add(arg);
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public String getString( Integer index ) {
        return arguments.get(index);
    }

    public Long getLong( Integer index ) {
        return Long.parseLong(arguments.get(index));
    }

    public Boolean getBoolean( Integer index ) {
        return Boolean.parseBoolean(arguments.get(index));
    }

    public Integer getInteger( Integer index ) {
        return Integer.parseInt( arguments.get(index) );
    }

    public Float getFload( Integer index ) {
        return Float.parseFloat( arguments.get(index));
    }

    public Double getDouble( Integer index) {
        return Double.parseDouble(arguments.get(index));
    }

}
