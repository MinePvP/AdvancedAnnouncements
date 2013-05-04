package ch.minepvp.announcer.config;

import java.io.File;
import java.util.List;

public interface Config {

    public Boolean getBoolean( String path );

    public Integer getInt( String path );

    public Long getLong( String path );

    public Double getDouble( String path );

    public String getString( String path );

    public List<String> getStringList( String path );

    public Integer getChildrenSize( String path );

    public void setValue( String path, Object value );

    public Boolean has( String path );

    public File getFile();

}
