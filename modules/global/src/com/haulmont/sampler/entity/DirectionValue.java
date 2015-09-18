package com.haulmont.sampler.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "SAMPLER_DIRECTION_VALUE")
@Entity(name = "sampler$DirectionValue")
public class DirectionValue extends StandardEntity {
    private static final long serialVersionUID = -1306140343177050355L;

    @Column(name = "DIRECTION", nullable = false)
    protected String direction;

    @Column(name = "VALUE_", nullable = false)
    protected Double value;

    public void setDirection(Direction direction) {
        this.direction = direction == null ? null : direction.getId();
    }

    public Direction getDirection() {
        return direction == null ? null : Direction.fromId(direction);
    }


    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }


}