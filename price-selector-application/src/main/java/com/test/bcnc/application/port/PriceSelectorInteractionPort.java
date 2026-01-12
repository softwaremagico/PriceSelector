package com.test.bcnc.application.port;


import com.test.bcnc.domain.price.Price;

import java.time.LocalDateTime;

/***
 * Defines the app logic and user cases. How external services can interact with Price domain.
 */
public interface PriceSelectorInteractionPort {

    Price findBy(Long productId, Long brand, LocalDateTime onDate);
}
