package com.accenture.acme.store.shippingservice;

import java.math.BigDecimal;

public interface ShippingService {
    BigDecimal calculateShippingCost(final String destinationZipCode, double weight) throws ShippingServiceException,
            ZipCodeNotFoundException;
}
