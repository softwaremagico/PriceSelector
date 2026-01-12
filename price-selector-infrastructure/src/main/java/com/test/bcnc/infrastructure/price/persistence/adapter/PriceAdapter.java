package com.test.bcnc.infrastructure.price.persistence.adapter;

import com.test.bcnc.application.exceptions.PriceNotFoundException;
import com.test.bcnc.application.port.PriceSelectorInteractionPort;
import com.test.bcnc.domain.price.Price;
import com.test.bcnc.infrastructure.price.persistence.PriceRepository;
import com.test.bcnc.infrastructure.price.persistence.mapper.PriceMapper;
import com.test.bcnc.infrastructure.price.persistence.model.PriceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PriceAdapter implements PriceSelectorInteractionPort {

    private final PriceRepository repository;
    private final PriceMapper priceMapper;

    public PriceAdapter(PriceRepository repository, PriceMapper priceMapper) {
        this.repository = repository;
        this.priceMapper = priceMapper;
    }


    @Override
    public Price findBy(Long productId, Long brand, LocalDateTime onDate) {
        final Optional<PriceEntity> priceEntity = repository.findTopByProductAndBrandAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
                productId, brand, onDate, onDate);
        return priceMapper.toDomain(priceEntity.orElseThrow(() ->
                new PriceNotFoundException(getClass(), "No price found with productId '" + productId + "' and brand '"
                        + brand + "' on date '" + onDate + "'")));
    }
}
