package com.test.bcnc.infrastructure.price.persistence;

import com.test.bcnc.infrastructure.price.persistence.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * JPA repository with Price actions.
 */
@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findTopByProductIdAndBrandIdAndStartDateTimeBeforeAndEndDateTimeAfterOrderByPriorityDesc(
            Long productId, Long brand, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<PriceEntity> findByProductId(Long productId);

    Optional<PriceEntity> findByProductIdAndPriceList(Long product, Long priceList);
}
