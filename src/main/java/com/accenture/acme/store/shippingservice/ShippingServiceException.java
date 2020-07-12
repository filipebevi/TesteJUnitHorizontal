package com.accenture.acme.store.shippingservice;

public class ShippingServiceException extends Exception {
    public ShippingServiceException(String message) {
        super(message);
    }
    public ShippingServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
