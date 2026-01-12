package com.test.bcnc.domain.price;

import java.time.LocalDateTime;

/**
 * Prices. Reflects the final price (RRP) and the rate applied to a product in a chain between certain dates.
 */
public class Price {
    private Long product;
    private Long priceList;
    private Long brand;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Long priority;
    private Double price;
    private String currency;

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brandId) {
        this.brand = brandId;
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

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
