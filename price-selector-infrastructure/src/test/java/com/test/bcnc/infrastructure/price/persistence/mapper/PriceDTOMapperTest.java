package com.test.bcnc.infrastructure.price.persistence.mapper;

import com.test.bcnc.domain.price.Price;
import com.test.bcnc.infrastructure.price.persistence.model.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

@Test(groups = "mapperTests")
@SpringBootTest
public class PriceDTOMapperTest extends AbstractTestNGSpringContextTests {
    private static final Long PRODUCT_ID = 35445L;
    private static final Long BRAND_ID = 1L;
    private static final Long PRICE_LIST = 1L;
    private static final Double PRICE = 35D;
    private static final Long PRIORITY = 2L;
    private static final LocalDateTime START_DATE = LocalDateTime.of(2020, 6, 14, 14, 0);
    private static final LocalDateTime END_DATE = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
    private static final String CURRENCY = "EUR";

    @Autowired
    private PriceMapper priceMapper;

    @Test
    void mapper_toResponse() {
        final PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setCurrency(CURRENCY);
        entity.setBrandId(BRAND_ID);
        entity.setPrice(PRICE);
        entity.setProductId(PRODUCT_ID);
        entity.setPriceList(PRICE_LIST);
        entity.setPriority(PRIORITY);
        entity.setStartDateTime(START_DATE);
        entity.setEndDateTime(END_DATE);

        Price domain = new Price(BRAND_ID, PRODUCT_ID, PRICE_LIST, PRIORITY, START_DATE, END_DATE, PRICE, CURRENCY);
        Price result = priceMapper.toDomain(entity);

        Assert.assertEquals(domain.getProductId(), result.getProductId());
        Assert.assertEquals(domain.getBrandId(), result.getBrandId());
        Assert.assertEquals(domain.getPriceList(), result.getPriceList());
        Assert.assertEquals(domain.getPriority(), result.getPriority());
        Assert.assertEquals(domain.getStartDate(), result.getStartDate());
        Assert.assertEquals(domain.getEndDate(), result.getEndDate());
        Assert.assertEquals(domain.getPrice(), result.getPrice());
        Assert.assertEquals(domain.getCurrency(), result.getCurrency());
    }

    @Test
    void mapper_toEntity() {
        final PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setBrandId(BRAND_ID);
        entity.setPrice(PRICE);
        entity.setProductId(PRODUCT_ID);
        entity.setPriceList(PRICE_LIST);
        entity.setPriority(PRIORITY);
        entity.setStartDateTime(START_DATE);
        entity.setEndDateTime(END_DATE);
        entity.setCurrency(CURRENCY);

        Price domain = new Price(BRAND_ID, PRODUCT_ID, PRICE_LIST, PRIORITY, START_DATE, END_DATE, PRICE, CURRENCY);

        PriceEntity result = priceMapper.toEntity(domain);

        Assert.assertEquals(entity.getProductId(), result.getProductId());
        Assert.assertEquals(entity.getBrandId(), result.getBrandId());
        Assert.assertEquals(entity.getPriceList(), result.getPriceList());
        Assert.assertEquals(entity.getPriority(), result.getPriority());
        Assert.assertEquals(entity.getStartDateTime(), result.getStartDateTime());
        Assert.assertEquals(entity.getEndDateTime(), result.getEndDateTime());
        Assert.assertEquals(entity.getPrice(), result.getPrice());
        Assert.assertEquals(entity.getCurrency(), result.getCurrency());
    }
}
