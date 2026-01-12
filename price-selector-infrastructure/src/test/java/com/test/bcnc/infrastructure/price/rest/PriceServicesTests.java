package com.test.bcnc.infrastructure.price.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bcnc.infrastructure.price.rest.dto.PriceDTO;
import com.test.bcnc.infrastructure.price.rest.dto.PriceRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/***
 * This class contains some custom tests. For the tests required on the exercise, please check class PriceRequestedExampleTests.
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@Test(groups = "priceServices")
public class PriceServicesTests extends AbstractTestNGSpringContextTests {
    private static final long PRODUCT_ID = 35455;
    private static final long BRAND_ID = 1;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private <T> String toJson(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private <T> T fromJson(String payload, Class<T> clazz) throws IOException {
        return objectMapper.readValue(payload, clazz);
    }

    @BeforeClass
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    public void searchForPriceOn14th_usingPost() throws Exception {
        final PriceRequest priceRequest = new PriceRequest(PRODUCT_ID, BRAND_ID,
                OffsetDateTime.of(LocalDateTime.of(2020, 6, 14, 10, 0), ZoneOffset.UTC));

        MvcResult createResult = this.mockMvc
                .perform(post("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(priceRequest))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final PriceDTO price1 = fromJson(createResult.getResponse().getContentAsString(), PriceDTO.class);
        Assert.assertEquals(price1.getProduct(), PRODUCT_ID);
        Assert.assertEquals(price1.getPriceList(), 1);
        Assert.assertEquals(price1.getBrand(), BRAND_ID);
        Assert.assertEquals(price1.getPrice(), 35.50);
    }

    @Test
    public void searchForPriceOn14th_usingGet() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("product", String.valueOf(PRODUCT_ID));
        requestParams.add("brand", String.valueOf(BRAND_ID));
        requestParams.add("on", LocalDateTime.of(2020, 6, 14, 10, 0)
                .atOffset(ZoneOffset.UTC).format(dateTimeFormatter));

        MvcResult createResult = this.mockMvc
                .perform(get("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final PriceDTO price1 = fromJson(createResult.getResponse().getContentAsString(), PriceDTO.class);
        Assert.assertEquals(price1.getProduct(), PRODUCT_ID);
        Assert.assertEquals(price1.getPriceList(), 1);
        Assert.assertEquals(price1.getBrand(), BRAND_ID);
        Assert.assertEquals(price1.getPrice(), 35.50);
    }


    @Test
    public void searchForPriceOn15th_usingPost() throws Exception {
        final PriceRequest priceRequest = new PriceRequest(PRODUCT_ID, BRAND_ID,
                OffsetDateTime.of(LocalDateTime.of(2020, 6, 15, 10, 0), ZoneOffset.UTC));

        MvcResult createResult = this.mockMvc
                .perform(post("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(priceRequest))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final PriceDTO price = fromJson(createResult.getResponse().getContentAsString(), PriceDTO.class);
        Assert.assertEquals(price.getProduct(), PRODUCT_ID);
        Assert.assertEquals(price.getPriceList(), 3);
        Assert.assertEquals(price.getBrand(), BRAND_ID);
        Assert.assertEquals(price.getPrice(), 30.50);
    }


    @Test
    public void searchForPriceOn15th_usingGet() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("product", String.valueOf(PRODUCT_ID));
        requestParams.add("brand", String.valueOf(BRAND_ID));
        requestParams.add("on", LocalDateTime.of(2020, 6, 15, 10, 0)
                .atOffset(ZoneOffset.UTC).format(dateTimeFormatter));

        MvcResult createResult = this.mockMvc
                .perform(get("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final PriceDTO price = fromJson(createResult.getResponse().getContentAsString(), PriceDTO.class);
        Assert.assertEquals(price.getProduct(), PRODUCT_ID);
        Assert.assertEquals(price.getPriceList(), 3);
        Assert.assertEquals(price.getBrand(), BRAND_ID);
        Assert.assertEquals(price.getPrice(), 30.50);
    }


    @Test
    public void searchForPriceOn20th_usingPost() throws Exception {
        final PriceRequest priceRequest = new PriceRequest(PRODUCT_ID, BRAND_ID,
                OffsetDateTime.of(LocalDateTime.of(2020, 6, 20, 10, 0), ZoneOffset.UTC));

        MvcResult createResult = this.mockMvc
                .perform(post("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(priceRequest))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final PriceDTO price = fromJson(createResult.getResponse().getContentAsString(), PriceDTO.class);
        Assert.assertEquals(price.getProduct(), PRODUCT_ID);
        Assert.assertEquals(price.getPriceList(), 4);
        Assert.assertEquals(price.getBrand(), BRAND_ID);
        //Price 4 has more priority.
        Assert.assertEquals(price.getPrice(), 38.95);
    }


    @Test
    public void searchForPriceOn20th_usingGet() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("product", String.valueOf(PRODUCT_ID));
        requestParams.add("brand", String.valueOf(BRAND_ID));
        requestParams.add("on", LocalDateTime.of(2020, 6, 20, 10, 0)
                .atOffset(ZoneOffset.UTC).format(dateTimeFormatter));

        MvcResult createResult = this.mockMvc
                .perform(get("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final PriceDTO price = fromJson(createResult.getResponse().getContentAsString(), PriceDTO.class);
        Assert.assertEquals(price.getProduct(), PRODUCT_ID);
        Assert.assertEquals(price.getPriceList(), 4);
        Assert.assertEquals(price.getBrand(), BRAND_ID);
        Assert.assertEquals(price.getPrice(), 38.95);
    }

    @Test
    public void searchForInvalidPrice_usingPost() throws Exception {
        final PriceRequest priceRequest = new PriceRequest(PRODUCT_ID, BRAND_ID,
                OffsetDateTime.of(LocalDateTime.of(2021, 6, 14, 10, 0), ZoneOffset.UTC));

        System.out.println("------------------------- Begin Expected Logged Exception -------------------------");
        this.mockMvc
                .perform(post("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(priceRequest))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        System.out.println("------------------------- Begin Expected Logged Exception -------------------------");
    }

    @Test
    public void searchForInvalidPrice_usingGet() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("product", String.valueOf(PRODUCT_ID));
        requestParams.add("brand", String.valueOf(BRAND_ID));
        requestParams.add("on", LocalDateTime.of(2021, 6, 20, 10, 0)
                .atOffset(ZoneOffset.UTC).format(dateTimeFormatter));

        System.out.println("------------------------- Begin Expected Logged Exception -------------------------");
        this.mockMvc
                .perform(get("/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        System.out.println("------------------------- Begin Expected Logged Exception -------------------------");
    }

}
