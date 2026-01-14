package com.test.bcnc.infrastructure.price.rest;

import com.test.bcnc.application.exceptions.PriceNotFoundException;
import com.test.bcnc.application.service.PriceService;
import com.test.bcnc.infrastructure.price.rest.model.PriceDTO;
import com.test.bcnc.infrastructure.price.rest.mapper.PriceDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * Provides an API REST for Price.
 */
@Validated
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
     * @param productId The product to search.
     * @param brandId   The brand where this product belongs to.
     * @param applicationDate      A date on ISO format for searching the price.
     * @return the price information.
     * @throws PriceNotFoundException If no price exists on the selected date.
     */
    @Operation(summary = "Finds a price from a search criteria.")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public PriceDTO get(@Parameter(name = "productId", required = true, example = "35455") @RequestParam(value = "productId") @Positive @NotNull Long productId,
                        @Parameter(name = "brandId", required = true, example = "1") @RequestParam(value = "brandId") @Positive @NotNull Long brandId,
                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                        @Parameter(description = "price on a selected date", required = true, example = "2020-06-15T16:00:00.00Z")
                        @RequestParam(value = "applicationDate") @NotNull OffsetDateTime applicationDate) throws PriceNotFoundException {
        return priceDTOMapper.toResponse(priceService.findBy(productId, brandId, LocalDateTime.ofInstant(applicationDate.toInstant(), ZoneId.of("UTC"))));
    }
}
