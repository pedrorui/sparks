package com.scitus.sparks.core;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ResourceLocator
{
    InputStream openFromClasspath(String resourceName);

    InputStream openFromFilesystem(String resourceName) throws FileNotFoundException;
}
