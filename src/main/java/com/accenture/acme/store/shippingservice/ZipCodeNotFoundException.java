package com.accenture.acme.store.shippingservice;

public class ZipCodeNotFoundException extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ZipCodeNotFoundException(String message) {
        super(message);
    }
}
