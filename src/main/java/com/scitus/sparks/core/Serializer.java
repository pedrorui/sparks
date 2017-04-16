package com.scitus.sparks.core;

public interface Serializer
{
    <T> T read(String content, Class<T> typeClass);

    <T> String write(T instance);
}
