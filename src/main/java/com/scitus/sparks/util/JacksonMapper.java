package com.scitus.sparks.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonMapper
{
    private JacksonMapper()
    {
    }

    public static ObjectMapper createDefaultMapper()
    {
        final ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule())
                .registerModule(new Jdk8Module())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
                .enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }
}
