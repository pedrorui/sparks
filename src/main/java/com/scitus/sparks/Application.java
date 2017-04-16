package com.scitus.sparks;

import com.scitus.sparks.core.Configuration;
import com.scitus.sparks.core.DefaultConfiguration;
import com.scitus.sparks.core.DefaultResourceLocator;
import com.scitus.sparks.core.DefaultTimeProvider;
import com.scitus.sparks.core.JsonSerializer;
import com.scitus.sparks.core.JsonTransformer;
import com.scitus.sparks.core.Resource;
import com.scitus.sparks.resources.AppointmentResource;
import com.scitus.sparks.services.InMemoryAppointmentService;
import com.scitus.sparks.util.JacksonMapper;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Application
{
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args)
    {
        MutablePicoContainer container = new PicoBuilder().build();
        wire(container);

        Configuration configuration = container.getComponent(Configuration.class);
        Server server = new Server(configuration);
        List<Resource> resources = container.getComponents(Resource.class);
        server.start(resources);
    }

    private static void wire(MutablePicoContainer container)
    {
        logger.info("Wiring up dependencies");

        container.addComponent(JacksonMapper.createDefaultMapper())
                .addComponent(JsonTransformer.class)
                .addComponent(JsonSerializer.class)
                .addComponent(InMemoryAppointmentService.class)
                .addComponent(AppointmentResource.class)
                .addComponent(DefaultResourceLocator.class)
                .addComponent(DefaultConfiguration.class)
                .addComponent(DefaultTimeProvider.class);

        logger.info("Dependencies wired");
    }
}
