package com.scitus.sparks.core;

import java.time.LocalDateTime;

public class DefaultTimeProvider implements TimeProvider
{
    @Override
    public long getTimeInMillis()
    {
        return System.currentTimeMillis();
    }

    @Override
    public LocalDateTime getCurrentDateTime()
    {
        return LocalDateTime.now();
    }
}
