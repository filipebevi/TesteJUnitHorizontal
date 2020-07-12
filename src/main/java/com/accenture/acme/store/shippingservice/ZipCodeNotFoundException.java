package com.accenture.acme.store.shippingservice;

public class ZipCodeNotFoundException extends Exception {
    public ZipCodeNotFoundException(String message) {
        super(message);
    }
}
