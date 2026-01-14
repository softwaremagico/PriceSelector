package com.test.bcnc.application.port;


import com.test.bcnc.domain.price.Price;

import java.time.LocalDateTime;

/***
 * Secondary port to interact with persistence layer.
 */
public interface PricePersistencePort {

    Price findBy(Long productId, Long brand, LocalDateTime applicationDate);
}
