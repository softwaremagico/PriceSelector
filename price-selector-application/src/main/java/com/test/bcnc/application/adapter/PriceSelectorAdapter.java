package com.test.bcnc.application.adapter;

import com.test.bcnc.application.port.PriceSelectorInteractionPort;
import com.test.bcnc.application.port.PriceSelectorPort;
import com.test.bcnc.domain.price.Price;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceSelectorAdapter implements PriceSelectorInteractionPort {
    private final PriceSelectorPort priceSelectorPort;

    public PriceSelectorAdapter(PriceSelectorPort priceSelectorPort) {
        this.priceSelectorPort = priceSelectorPort;
    }

    @Override
    public Price findBy(Long productId, Long brand, LocalDateTime onDate) {
        return priceSelectorPort.findBy(productId, brand, onDate);
    }
}
