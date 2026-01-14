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
        return new Price(entity.getBrandId(), entity.getProductId(), entity.getPriceList(), entity.getPriority(), entity.getStartDateTime(),
                entity.getEndDateTime(), entity.getPrice(), entity.getCurrency());
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
