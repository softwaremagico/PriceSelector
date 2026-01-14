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

    @Autowired
    private PriceMapper priceMapper;

    @Test
    void mapper_toResponse() {
        final  LocalDateTime testDate = LocalDateTime.now();

        PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setCurrency("USD");
        entity.setBrandId(1L);
        entity.setPrice(42D);
        entity.setProductId(35445L);
        entity.setPriceList(1L);
        entity.setPriority(2L);
        entity.setStartDateTime(testDate.minusDays(1));
        entity.setEndDateTime(testDate.plusDays(1));

        Price domain = new Price();
        domain.setId(1L);
        domain.setCurrency("USD");
        domain.setBrandId(1L);
        domain.setPrice(42D);
        domain.setProductId(35445L);
        domain.setPriceList(1L);
        domain.setPriority(2L);
        domain.setStartDate(testDate.minusDays(1));
        domain.setEndDate(testDate.plusDays(1));

        Price result = priceMapper.toDomain(entity);

        Assert.assertEquals(domain, result);
    }

    @Test
    void mapper_toEntity() {
        final  LocalDateTime testDate = LocalDateTime.now();

        PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setCurrency("USD");
        entity.setBrandId(1L);
        entity.setPrice(42D);
        entity.setProductId(35445L);
        entity.setPriceList(1L);
        entity.setPriority(2L);
        entity.setStartDateTime(testDate.minusDays(1));
        entity.setEndDateTime(testDate.plusDays(1));

        Price domain = new Price();
        domain.setId(1L);
        domain.setCurrency("USD");
        domain.setBrandId(1L);
        domain.setPrice(42D);
        domain.setProductId(35445L);
        domain.setPriceList(1L);
        domain.setPriority(2L);
        domain.setStartDate(testDate.minusDays(1));
        domain.setEndDate(testDate.plusDays(1));

        PriceEntity result = priceMapper.toEntity(domain);

        Assert.assertEquals(entity, result);
    }
}
