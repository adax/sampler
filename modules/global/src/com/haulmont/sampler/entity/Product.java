package com.haulmont.sampler.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@NamePattern("%s|name")
@Table(name = "SAMPLER_PRODUCT")
@Entity(name = "sampler$Product")
public class Product extends StandardEntity {
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "PRICE", nullable = false)
    protected BigDecimal price;

    private static final long serialVersionUID = 4256660269544840258L;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }


}