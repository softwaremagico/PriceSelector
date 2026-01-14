package com.test.bcnc.infrastructure.price.persistence.adapter;

import com.test.bcnc.application.exceptions.PriceNotFoundException;
import com.test.bcnc.application.port.PricePersistencePort;
import com.test.bcnc.domain.price.Price;
import com.test.bcnc.infrastructure.price.persistence.PriceRepository;
import com.test.bcnc.infrastructure.price.persistence.mapper.PriceMapper;
import com.test.bcnc.infrastructure.price.persistence.model.PriceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/***
 * Defines the implementation of the app logic with the Spring Data JPA.
 */
@Component
public class PriceJpaAdapter implements PricePersistencePort {

    private final PriceRepository repository;
    private final PriceMapper priceMapper;

    public PriceJpaAdapter(PriceRepository repository, PriceMapper priceMapper) {
        this.repository = repository;
        this.priceMapper = priceMapper;
    }


    @Override
    public Price findBy(Long productId, Long brand, LocalDateTime applicationDate) {
        final Optional<PriceEntity> priceEntity = repository.findTopByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
                productId, brand, applicationDate, applicationDate);
        return priceMapper.toDomain(priceEntity.orElseThrow(() ->
                new PriceNotFoundException(getClass(), "No price found with productId '" + productId + "' and brand '"
                        + brand + "' on date '" + applicationDate + "'")));
    }
}
