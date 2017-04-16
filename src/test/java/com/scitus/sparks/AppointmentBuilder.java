package com.scitus.sparks;

import com.scitus.sparks.model.Appointment;

import java.time.LocalDateTime;

public class AppointmentBuilder implements Builder<Appointment> {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime date;

    public AppointmentBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public AppointmentBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public AppointmentBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public AppointmentBuilder setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    @Override
    public Appointment build() {
        return new Appointment(id, title, description, date);
    }
}
