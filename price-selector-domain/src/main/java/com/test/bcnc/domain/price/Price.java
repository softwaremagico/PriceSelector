package com.test.bcnc.domain.price;

import java.time.LocalDateTime;

/**
 * Prices. Reflects the final price (RRP) and the rate applied to a product in a chain between certain dates.
 */
public class Price {
    private static final int HASH_SEED = 31;

    private Long id;
    private Long productId;
    private Long priceList;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priority;
    private Double price;
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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

        return id.equals(price1.id) && productId.equals(price1.productId) && priceList.equals(price1.priceList)
                && brandId.equals(price1.brandId) && startDate.isEqual(price1.startDate) && endDate.isEqual(price1.endDate)
                && priority.equals(price1.priority) && price.equals(price1.price) && currency.equals(price1.currency);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = HASH_SEED * result + productId.hashCode();
        result = HASH_SEED * result + priceList.hashCode();
        result = HASH_SEED * result + brandId.hashCode();
        result = HASH_SEED * result + startDate.hashCode();
        result = HASH_SEED * result + endDate.hashCode();
        result = HASH_SEED * result + priority.hashCode();
        result = HASH_SEED * result + price.hashCode();
        result = HASH_SEED * result + currency.hashCode();
        return result;
    }
}
