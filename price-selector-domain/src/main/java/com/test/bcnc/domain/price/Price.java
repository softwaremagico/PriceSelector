package com.test.bcnc.domain.price;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Prices. Reflects the final price (RRP) and the rate applied to a product in a chain between certain dates.
 */
public class Price {
    private static final int HASH_SEED = 31;

    private final Long productId;
    private final Long priceList;
    private final Long brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Long priority;
    private final Double price;
    private final String currency;

    public Price(Long brandId,
                 Long productId,
                 Long priceList,
                 Long priority,
                 LocalDateTime startDateTime,
                 LocalDateTime endDateTime,
                 Double price,
                 String currency) {

        Objects.requireNonNull(startDateTime, "startDateTime must not be null");
        Objects.requireNonNull(endDateTime, "endDateTime must not be null");
        Objects.requireNonNull(price, "price must not be null");
        Objects.requireNonNull(currency, "currency must not be null");

        if (brandId <= 0) {
            throw new IllegalArgumentException("brandId must be positive");
        }
        if (productId <= 0) {
            throw new IllegalArgumentException("productId must be positive");
        }
        if (priceList <= 0) {
            throw new IllegalArgumentException("priceList must be positive");
        }
        if (priority < 0) {
            throw new IllegalArgumentException("priority must be >= 0");
        }
        if (endDateTime.isBefore(startDateTime)) {
            throw new IllegalArgumentException("endDateTime must be >= startDateTime");
        }

        this.productId = productId;
        this.priceList = priceList;
        this.brandId = brandId;
        this.price = price;
        this.startDate = startDateTime;
        this.endDate = endDateTime;
        this.priority = priority;
        this.currency = currency;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getPriceList() {
        return priceList;
    }

    public Long getBrandId() {
        return brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Long getPriority() {
        return priority;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    /**
     * Business rule: A price is applicable if the application date is within
     * the configured [startDateTime, endDateTime] range (inclusive).
     */
    public boolean appliesTo(LocalDateTime at) {
        return !at.isBefore(startDate) && !at.isAfter(endDate);
    }

    /**
     * Business rule: If multiple prices apply, the one with higher priority wins.
     */
    public boolean hasHigherPriorityThan(Price other) {
        Objects.requireNonNull(other, "other must not be null");
        return this.priority > other.priority;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Price price1)) {
            return false;
        }

        return productId.equals(price1.productId) && priceList.equals(price1.priceList)
                && brandId.equals(price1.brandId) && startDate.isEqual(price1.startDate) && endDate.isEqual(price1.endDate)
                && priority.equals(price1.priority) && price.equals(price1.price) && currency.equals(price1.currency);
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
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
