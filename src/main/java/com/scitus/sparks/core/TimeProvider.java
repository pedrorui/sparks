package com.scitus.sparks.core;

import java.time.LocalDateTime;

public interface TimeProvider
{
    long getTimeInMillis();
    LocalDateTime getCurrentDateTime();
}
