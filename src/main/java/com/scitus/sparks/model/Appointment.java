package com.scitus.sparks.model;

import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;

public class Appointment extends Entity<Integer>
{
    private String title;
    private String description;
    private LocalDateTime date;

    public Appointment() {}

    public Appointment(final Integer id, final String title, final String description, final LocalDateTime date)
    {
        setId(id);
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public void setDate(LocalDateTime date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("title", title)
                .add("description", description)
                .add("date", date)
                .toString();
    }
}
