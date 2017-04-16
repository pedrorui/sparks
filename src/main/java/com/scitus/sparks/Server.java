package com.scitus.sparks;

import com.scitus.sparks.core.Configuration;
import com.scitus.sparks.core.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.port;

class Server
{
    private static Logger logger = LoggerFactory.getLogger(Server.class);

    private final Configuration configuration;

    Server(final Configuration configuration)
    {
        this.configuration = configuration;
    }

    void start(final List<Resource> resources)
    {
        externalStaticFileLocation(configuration.get("config.server.staticFileLocation", "web"));
        port(configuration.getAsInt("config.server.portNumber", 8089));

        configureResources(resources);
    }

    private void configureResources(final List<Resource> resources)
    {
        logger.info("Setting up resources");
        resources.forEach(Resource::configure);
        logger.info("Resources configured");
    }
}
