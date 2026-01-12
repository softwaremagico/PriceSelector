package com.test.bcnc.infrastructure.price.rest.dto;

import java.time.OffsetDateTime;

public class PriceRequest {
    private Long product;

    private Long brand;

    private OffsetDateTime on;

    public PriceRequest() {
        super();
    }

    public PriceRequest(Long product, Long brand, OffsetDateTime on) {
        this();
        this.product = product;
        this.brand = brand;
        this.on = on;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }

    public OffsetDateTime getOn() {
        return on;
    }

    public void setOn(OffsetDateTime on) {
        this.on = on;
    }
}
