package com.test.bcnc.infrastructure.price.persistence.mapper;

import com.test.bcnc.domain.price.Price;
import com.test.bcnc.infrastructure.price.persistence.model.PriceEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public Price toDomain(PriceEntity entity) {
        if (entity == null) {
            return null;
        }
        final Price price = new Price();
        BeanUtils.copyProperties(entity, price, ConverterUtils.getNullPropertyNames(entity));
        price.setStartDate(entity.getStartDateTime());
        price.setEndDate(entity.getEndDateTime());
        return price;
    }

    public PriceEntity toEntity(Price price) {
        if (price == null) {
            return null;
        }
        final PriceEntity entity = new PriceEntity();
        BeanUtils.copyProperties(price, entity);
        entity.setStartDateTime(price.getStartDate());
        entity.setEndDateTime(price.getEndDate());
        return entity;
    }
}
