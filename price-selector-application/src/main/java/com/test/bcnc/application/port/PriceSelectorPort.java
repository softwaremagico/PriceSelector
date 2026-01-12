package com.test.bcnc.application.port;



import com.test.bcnc.domain.price.Price;

import java.time.LocalDateTime;

public interface PriceSelectorPort {

    Price findBy(Long productId, Long brand, LocalDateTime onDate);
}
