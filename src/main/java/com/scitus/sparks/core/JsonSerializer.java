package com.scitus.sparks.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonSerializer implements Serializer
{
    private final Logger logger = LoggerFactory.getLogger(JsonSerializer.class);
    private final ObjectMapper mapper;

    public JsonSerializer(final ObjectMapper mapper)
    {
        this.mapper = mapper;
    }

    @Override
    public <T> T read(final String content, final Class<T> typeClass)
    {
        try
        {
            return mapper.readValue(content, typeClass);
        }
        catch (IOException exception)
        {
            logger.error("Error reading the object contents.", exception);
        }

        return null;
    }

    @Override
    public <T> String write(final T instance)
    {
        try
        {
            return mapper.writeValueAsString(instance);
        }
        catch (JsonProcessingException exception)
        {
            logger.error("Error writing the object contents.", exception);
        }

        return null;
    }
}
