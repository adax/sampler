package com.haulmont.sampler.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import java.util.Date;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

@MetaClass(name = "sampler$DateValueVolume")
public class DateValueVolume extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = 1644204809967660606L;

    @MetaProperty(mandatory = true)
    protected Date date;

    @MetaProperty(mandatory = true)
    protected Long value;

    @MetaProperty(mandatory = true)
    protected Long volume;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getVolume() {
        return volume;
    }
}