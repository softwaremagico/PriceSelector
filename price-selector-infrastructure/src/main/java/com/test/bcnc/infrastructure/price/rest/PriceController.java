package com.test.bcnc.infrastructure.price.rest;

import com.test.bcnc.application.exceptions.PriceNotFoundException;
import com.test.bcnc.application.service.PriceService;
import com.test.bcnc.infrastructure.price.rest.dto.PriceDTO;
import com.test.bcnc.infrastructure.price.rest.dto.PriceRequest;
import com.test.bcnc.infrastructure.price.rest.dto.mapper.PriceDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * Provides an API REST for Price.
 */
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;
    private final PriceDTOMapper priceDTOMapper;

    public PriceController(PriceService priceService, PriceDTOMapper priceDTOMapper) {
        this.priceService = priceService;
        this.priceDTOMapper = priceDTOMapper;
    }


    /**
     * Finds a price from a search criteria.
     *
     * @param product The product to search.
     * @param brand   The brand where this product belongs to.
     * @param on      A date on ISO format for searching the price.
     * @return the price information.
     * @throws PriceNotFoundException If no price exists on the selected date.
     */
    @Operation(summary = "Finds a price from a search criteria.")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public PriceDTO get(@Parameter(name = "product", required = true, example = "35455") @RequestParam(value = "product") Long product,
                        @Parameter(name = "brand", required = true, example = "1") @RequestParam(value = "brand") Long brand,
                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                        @Parameter(description = "price on a selected date", required = true, example = "2020-06-15T16:00:00.00Z")
                        @RequestParam(value = "on") OffsetDateTime on) throws PriceNotFoundException {
        return priceDTOMapper.toResponse(priceService.findBy(product, brand, LocalDateTime.ofInstant(on.toInstant(), ZoneId.of("UTC"))));
    }


    /**
     * Finds a price from a search criteria.
     *
     * @param priceRequest Payload containing the product, brand and date of search.
     * @return a price
     * @throws PriceNotFoundException if no price is found.
     */
    @Operation(summary = "Finds a price from a search criteria.")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public PriceDTO post(@RequestBody PriceRequest priceRequest) throws PriceNotFoundException {
        return priceDTOMapper.toResponse(priceService.findBy(priceRequest.getProduct(), priceRequest.getBrand(),
                LocalDateTime.ofInstant(priceRequest.getOn().toInstant(), ZoneId.of("UTC"))));
    }
}
