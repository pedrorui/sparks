package com.scitus.sparks.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class DefaultConfiguration implements Configuration
{
    private static Logger logger = LoggerFactory.getLogger(DefaultConfiguration.class);

    private final ConcurrentMap<String, Properties> propertiesCache = new ConcurrentHashMap<>();
    private final ResourceLocator resourceLocator;

    public DefaultConfiguration(final ResourceLocator resourceLocator)
    {
        this.resourceLocator = resourceLocator;
    }

    @Override
    public String get(final String parameterName)
    {
        int splitPosition = parameterName.indexOf('.');

        String file = parameterName.substring(0, splitPosition);
        String propertyName = parameterName.substring(splitPosition + 1);

        String property = getProperties(file).getProperty(propertyName);

        return property != null ? property.trim() : null;
    }

    @Override
    public String get(final String parameterName, final String defaultValue)
    {
        String parameter = get(parameterName);
        return parameter != null ? parameter : defaultValue;
    }

    @Override
    public int getAsInt(final String parameterName, final int defaultValue)
    {
        if (get(parameterName) == null)
        {
            return defaultValue;
        }

        try
        {
            return Integer.parseInt(get(parameterName));
        }
        catch (NumberFormatException e)
        {
            return defaultValue;
        }
    }

    @Override
    public long getAsLong(final String parameterName, final long defaultValue)
    {
        if (get(parameterName) == null)
        {
            return defaultValue;
        }

        try
        {
            return Long.parseLong(get(parameterName));
        }
        catch (NumberFormatException e)
        {
            return defaultValue;
        }
    }

    @Override
    public Iterator<String> getNames(final String prefix)
    {
        String file;
        if (prefix.contains("."))
        {
            file = prefix.substring(0, prefix.indexOf('.'));
        }
        else
        {
            file = prefix;
        }

        Properties props = getProperties(file);
        List<String> nameList = props.stringPropertyNames().stream().filter(name -> name.startsWith(prefix) || name.equals("")).collect(Collectors.toCollection(LinkedList::new));
        Collections.sort(nameList);
        return (Collections.unmodifiableList(nameList)).listIterator();
    }

    @Override
    public Map<String, String> getMap(final String prefix)
    {
        String file;
        if (prefix.contains("."))
        {
            file = prefix.substring(0, prefix.indexOf('.'));
        }
        else
        {
            file = prefix;
        }

        Properties properties = getProperties(file);
        Map<String, String> map = new HashMap<>();
        properties.stringPropertyNames().stream().filter(name -> name.startsWith(prefix) || name.equals("")).forEach(name -> map.put(name, properties.getProperty(name)));
        return Collections.unmodifiableMap(map);
    }

    @Override
    public void flush(final String parameterName)
    {
        String file = parameterName.substring(0, parameterName.indexOf('.'));
        propertiesCache.remove(file);
    }

    @Override
    public boolean getAsBoolean(final String parameterName, final boolean defaultValue)
    {
        if (get(parameterName) == null)
        {
            return defaultValue;
        }
        return Boolean.parseBoolean(get(parameterName));
    }

    private Properties getProperties(final String file)
    {
        Properties properties = propertiesCache.get(file);
        if (properties == null)
        {
            properties = loadProperties(file);
            propertiesCache.putIfAbsent(file, properties);
        }
        return properties;
    }

    private Properties loadProperties(final String file)
    {
        if (propertiesCache.containsKey(file))
        {
            return propertiesCache.get(file);
        }

        Properties properties = new Properties();
        String resourceName = file + ".properties";

        try (InputStream resourceAsStream = resourceLocator.openFromClasspath(resourceName))
        {
            if (resourceAsStream == null)
            {
                logger.warn("No configuration file called " + resourceName + " in classpath");
            }
            else
            {
                properties.load(resourceAsStream);
            }
        }
        catch (IOException exception)
        {
            logger.error("Error loading properties", exception);
        }
        return properties;
    }
}
