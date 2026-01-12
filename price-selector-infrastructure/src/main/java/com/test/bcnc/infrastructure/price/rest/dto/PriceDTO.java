package com.test.bcnc.infrastructure.price.rest.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class for transferring price information to external services.
 */
public class PriceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6160402494109739147L;

    private Long product;

    private Long brand;

    private Long priceList;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Double price;

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

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
