package com.scitus.sparks.core;

import java.util.Iterator;
import java.util.Map;

public interface Configuration
{
    String get(String parameterName);

    String get(String parameterName, String defaultValue);

    int getAsInt(String parameterName, int defaultValue);

    long getAsLong(String parameterName, long defaultValue);

    boolean getAsBoolean(String parameterName, boolean defaultValue);

    Iterator<String> getNames(String prefix);

    Map<String, String> getMap(String prefix);

    void flush(String parameterName);
}
