package com.scitus.sparks.services;

import com.scitus.sparks.model.Appointment;

import java.util.Collection;

public interface AppointmentService<T>
{
    Collection<Appointment> list();

    Appointment get(T id);

    Appointment create(Appointment appointment);

    Appointment delete(T id);
}
