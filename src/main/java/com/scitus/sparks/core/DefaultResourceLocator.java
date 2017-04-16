package com.scitus.sparks.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DefaultResourceLocator implements ResourceLocator
{
    @Override
    public InputStream openFromClasspath(final String resourceName)
    {
        return this.getClass().getClassLoader().getResourceAsStream(resourceName);
    }

    @Override
    public InputStream openFromFilesystem(final String realPath) throws FileNotFoundException
    {
        return new FileInputStream(realPath);
    }
}
