package com.scitus.sparks.services;

import com.scitus.sparks.core.TimeProvider;
import com.scitus.sparks.model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAppointmentService implements AppointmentService<Integer>
{
    private static Logger logger = LoggerFactory.getLogger(InMemoryAppointmentService.class);

    private final TimeProvider timeProvider;
    private final Map<Integer, Appointment> appointments = new ConcurrentHashMap<>();

    public InMemoryAppointmentService(final TimeProvider timeProvider)
    {
        this.timeProvider = timeProvider;
    }

    @Override
    public Collection<Appointment> list()
    {
        logger.info("Getting all appointments at " + timeProvider.getCurrentDateTime());
        return appointments.values();
    }

    @Override
    public Appointment get(final Integer id)
    {
        logger.info("Getting appointment: " + id);
        return appointments.values()
                .stream()
                .filter(appointment -> appointment.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find appointment: " + id));
    }

    @Override
    public Appointment create(final Appointment appointment)
    {
        if (appointment.isTransient())
        {
            appointment.setId(appointments.size() + 1);
            appointments.putIfAbsent(appointment.getId(), appointment);

            logger.info("Added appointment: " + appointment + ", at " + timeProvider.getCurrentDateTime());
        }
        else
        {
            appointments.put(appointment.getId(), appointment);
            logger.info("Edited appointment" + appointment + ", at " + timeProvider.getCurrentDateTime());
        }

        return appointment;
    }

    @Override
    public Appointment delete(Integer id)
    {
        return appointments.remove(id);
    }
}
