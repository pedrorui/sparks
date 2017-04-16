package com.scitus.sparks.model;

import java.beans.Transient;

public class Entity<T>
{
    private T id;

    public T getId()
    {
        return id;
    }

    public void setId(T id)
    {
        this.id = id;
    }

    @Transient
    public boolean isTransient()
    {
        return id == null;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Entity<?> entity = (Entity<?>) other;

        return id != null ? id.equals(entity.id) : entity.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
