package com.scitus.sparks.core;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer
{
    private final Serializer serializer;

    public JsonTransformer(final Serializer serializer)
    {
        this.serializer = serializer;
    }

    @Override
    public String render(final Object model) throws Exception
    {
        return serializer.write(model);
    }
}
