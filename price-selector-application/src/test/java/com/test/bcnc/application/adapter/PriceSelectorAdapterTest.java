package com.test.bcnc.application.adapter;


import com.test.bcnc.application.exceptions.PriceNotFoundException;
import com.test.bcnc.domain.price.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

@Test(groups = {"priceAdapter"})
@SpringBootTest
public class PriceSelectorAdapterTest extends AbstractTestNGSpringContextTests {
    private static final long PRODUCT_ID = 35455;
    private static final long BRAND_ID = 1;

    @Autowired
    private PriceSelectorAdapter priceSelectorAdapter;

    @Test
    public void getPriceListOnFirstDesiredDates() {
        Price price1 = priceSelectorAdapter.findBy(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 14, 10, 0));
        Assert.assertEquals(price1.getProductId(), PRODUCT_ID);
        Assert.assertEquals(price1.getPriceList(), 1);

        Price price2 = priceSelectorAdapter.findBy(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 14, 16, 0));
        Assert.assertEquals(price2.getProductId(), PRODUCT_ID);
        Assert.assertEquals(price2.getPriceList(), 2);

        Price price3 = priceSelectorAdapter.findBy(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 14, 21, 0));
        Assert.assertEquals(price3.getProductId(), PRODUCT_ID);
        Assert.assertEquals(price3.getPriceList(), 1);

        Price price4 = priceSelectorAdapter.findBy(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 15, 10, 0));
        Assert.assertEquals(price4.getProductId(), PRODUCT_ID);
        Assert.assertEquals(price4.getPriceList(), 3);

        Price price5 = priceSelectorAdapter.findBy(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2020, 6, 16, 21, 0));
        Assert.assertEquals(price5.getProductId(), PRODUCT_ID);
        Assert.assertEquals(price5.getPriceList(), 4);
    }

    @Test(expectedExceptions = PriceNotFoundException.class)
    public void getNonExistingPrice() {
        priceSelectorAdapter.findBy(
                PRODUCT_ID, BRAND_ID,
                LocalDateTime.of(2099, 6, 14, 10, 0));
    }
}
