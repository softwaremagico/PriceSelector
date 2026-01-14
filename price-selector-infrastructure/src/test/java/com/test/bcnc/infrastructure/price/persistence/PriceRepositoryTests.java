package com.test.bcnc.infrastructure.price.persistence;

import com.test.bcnc.infrastructure.price.persistence.model.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Optional;

@Test(groups = {"priceRepository"})
@SpringBootTest
public class PriceRepositoryTests extends AbstractTestNGSpringContextTests {
    private static final long PRODUCT_ID = 35455;
    private static final long BRAND_ID = 1;

    @Autowired
    private PriceRepository priceRepository;

    @Test
    public void checkCurrentDatabaseContent() {
        Assert.assertEquals(priceRepository.count(), 4);
        Assert.assertEquals(priceRepository.findByProductId(PRODUCT_ID).size(), 4);
    }

    @Test
    public void checkStartAndEndDatesFromPrices() {
        final Optional<PriceEntity> secondPrice = priceRepository.findByProductIdAndPriceList(PRODUCT_ID, 2L);
        Assert.assertTrue(secondPrice.isPresent());
        Assert.assertEquals(secondPrice.get().getStartDateTime(), LocalDateTime.of(2020, 6, 14, 15, 0));
        Assert.assertEquals(secondPrice.get().getEndDateTime(), LocalDateTime.of(2020, 6, 14, 18, 30));
    }

    @Test
    public void getPriceListOnFirstDesiredDates() {
        Optional<PriceEntity> price1 = priceRepository.findTopByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 14, 10, 0),
                LocalDateTime.of(2020, 6, 14, 10, 0));
        Assert.assertTrue(price1.isPresent());
        Assert.assertEquals(price1.get().getProductId(), PRODUCT_ID);
        Assert.assertEquals(price1.get().getPriceList(), 1);

        Optional<PriceEntity> price2 = priceRepository.findTopByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 14, 16, 0),
                LocalDateTime.of(2020, 6, 14, 16, 0));
        Assert.assertTrue(price2.isPresent());
        Assert.assertEquals(price2.get().getProductId(), PRODUCT_ID);
        Assert.assertEquals(price2.get().getPriceList(), 2);

        Optional<PriceEntity> price3 = priceRepository.findTopByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 14, 21, 0),
                LocalDateTime.of(2020, 6, 14, 21, 0));
        Assert.assertTrue(price3.isPresent());
        Assert.assertEquals(price3.get().getProductId(), PRODUCT_ID);
        Assert.assertEquals(price3.get().getPriceList(), 1);

        Optional<PriceEntity> price4 = priceRepository.findTopByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 15, 10, 0),
                LocalDateTime.of(2020, 6, 15, 10, 0));
        Assert.assertTrue(price4.isPresent());
        Assert.assertEquals(price4.get().getProductId(), PRODUCT_ID);
        Assert.assertEquals(price4.get().getPriceList(), 3);

        Optional<PriceEntity> price5 = priceRepository.findTopByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 16, 21, 0),
                LocalDateTime.of(2020, 6, 16, 21, 0));
        Assert.assertTrue(price5.isPresent());
        Assert.assertEquals(price5.get().getProductId(), PRODUCT_ID);
        Assert.assertEquals(price5.get().getPriceList(), 4);
    }
}
