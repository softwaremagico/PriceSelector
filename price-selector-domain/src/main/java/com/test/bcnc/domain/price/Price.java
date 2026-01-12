package com.test.bcnc.domain.price;

import java.time.LocalDateTime;

/**
 * Prices. Reflects the final price (RRP) and the rate applied to a product in a chain between certain dates.
 */
public class Price {
    private static final int HASH_SEED = 31;

    private Long id;
    private Long product;
    private Long priceList;
    private Long brand;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Long priority;
    private Double price;
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Price price1)) {
            return false;
        }

        return id.equals(price1.id) && product.equals(price1.product) && priceList.equals(price1.priceList)
                && brand.equals(price1.brand) && startDateTime.isEqual(price1.startDateTime) && endDateTime.isEqual(price1.endDateTime)
                && priority.equals(price1.priority) && price.equals(price1.price) && currency.equals(price1.currency);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = HASH_SEED * result + product.hashCode();
        result = HASH_SEED * result + priceList.hashCode();
        result = HASH_SEED * result + brand.hashCode();
        result = HASH_SEED * result + startDateTime.hashCode();
        result = HASH_SEED * result + endDateTime.hashCode();
        result = HASH_SEED * result + priority.hashCode();
        result = HASH_SEED * result + price.hashCode();
        result = HASH_SEED * result + currency.hashCode();
        return result;
    }
}
