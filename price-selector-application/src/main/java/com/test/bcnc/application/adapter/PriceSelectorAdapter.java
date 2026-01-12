package com.test.bcnc.application.adapter;

import com.test.bcnc.application.port.PriceSelectorInteractionPort;
import com.test.bcnc.application.port.PricePersistencePort;
import com.test.bcnc.domain.price.Price;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Adapter between the user cases on PriceSelectorInteractionPort and the infrastructure layer accessed through PriceSelectorPort.
 */
@Component
public class PriceSelectorAdapter implements PriceSelectorInteractionPort {
    private final PricePersistencePort pricePersistencePort;

    public PriceSelectorAdapter(PricePersistencePort pricePersistencePort) {
        this.pricePersistencePort = pricePersistencePort;
    }

    @Override
    public Price findBy(Long productId, Long brand, LocalDateTime onDate) {
        return pricePersistencePort.findBy(productId, brand, onDate);
    }
}
