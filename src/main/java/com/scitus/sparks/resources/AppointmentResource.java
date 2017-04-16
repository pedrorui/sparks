package com.scitus.sparks.resources;

import com.scitus.sparks.HttpConstants;
import com.scitus.sparks.core.Configuration;
import com.scitus.sparks.core.Resource;
import com.scitus.sparks.core.Serializer;
import com.scitus.sparks.model.Appointment;
import com.scitus.sparks.services.AppointmentService;
import spark.Request;
import spark.Response;
import spark.ResponseTransformer;

import java.util.Collection;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

public class AppointmentResource implements Resource
{
    private static final String DEFAULT_API_URL = "/api/appointment";

    private final ResponseTransformer responseTransformer;
    private final AppointmentService<Integer> appointmentService;
    private final Serializer serializer;
    private final Configuration configuration;

    public AppointmentResource(final ResponseTransformer responseTransformer, final AppointmentService<Integer> appointmentService, final Serializer serializer, final Configuration configuration)
    {
        this.responseTransformer = responseTransformer;
        this.appointmentService = appointmentService;
        this.serializer = serializer;
        this.configuration = configuration;
    }

    @Override
    public void configure()
    {
        String apiUrl = configuration.get("config.resources.appointment.url", DEFAULT_API_URL);

        path(apiUrl, () ->
        {
            get("",  this::list, responseTransformer);
            get("/:id", this::insertOrUpdate, responseTransformer);
            post("", HttpConstants.MimeTypes.APPLICATION_JSON, this::create, responseTransformer);
            delete("/:id", this::remove, responseTransformer);
        });
    }

    private Appointment create(Request request, Response response)
    {
        Appointment appointment = serializer.read(request.body(), Appointment.class);
        response.status(HttpConstants.StatusCodes.CREATED);
        return appointmentService.create(appointment);
    }

    private Collection<Appointment> list(Request request, Response response)
    {
        response.status(HttpConstants.StatusCodes.OK);
        return appointmentService.list();
    }

    private Appointment insertOrUpdate(Request request, Response response)
    {
        int appointmentId = getAppointmentId(request);
        response.status(HttpConstants.StatusCodes.OK);
        return appointmentService.get(appointmentId);
    }

    private Appointment remove(Request request, Response response)
    {
        int appointmentId = getAppointmentId(request);
        response.status(HttpConstants.StatusCodes.OK);
        return appointmentService.delete(appointmentId);
    }

    private int getAppointmentId(Request request)
    {
        return Integer.parseInt(request.params(":id"));
    }
}
