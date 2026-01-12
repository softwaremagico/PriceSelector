package com.test.bcnc.infrastructure.price.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Stores a price into a database table.
 */
@Entity
@Table(name = "PRICES", indexes = {
        @Index(name = "ind_product_id", columnList = "PRODUCT_ID"),
        @Index(name = "ind_price_list", columnList = "PRICE_LIST"),
}, uniqueConstraints = {
        @UniqueConstraint(name = "onePriceByList", columnNames = {"PRODUCT_ID", "PRICE_LIST"})
})
public class PriceEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -7178013292587511813L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long product;

    @Column(name = "PRICE_LIST")
    private Long priceList;

    @Column(name = "BRAND_ID")
    private Long brand;

    @Column(name = "START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime startDateTime;

    @Column(name = "END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime endDateTime;

    @Column(name = "PRIORITY")
    private Long priority;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CURR")
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
        if (!(o instanceof PriceEntity comparedPriceEntity)) {
            return false;
        }

        return id.equals(comparedPriceEntity.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
