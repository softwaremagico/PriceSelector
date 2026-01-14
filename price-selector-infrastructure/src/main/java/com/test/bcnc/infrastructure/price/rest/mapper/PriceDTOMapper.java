package com.test.bcnc.infrastructure.price.rest.mapper;

import com.test.bcnc.domain.price.Price;
import com.test.bcnc.infrastructure.price.persistence.mapper.ConverterUtils;
import com.test.bcnc.infrastructure.price.rest.model.PriceDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PriceDTOMapper {

    public PriceDTO toResponse(Price price) {
        if (price == null) {
            return null;
        }
        final PriceDTO priceDTO = new PriceDTO();
        BeanUtils.copyProperties(price, priceDTO, ConverterUtils.getNullPropertyNames(price));
        return priceDTO;
    }
}
