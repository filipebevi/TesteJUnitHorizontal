package com.accenture.acme.store.shippingservice;

public class ShippingServiceException extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = 5908380799103186093L;

    public ShippingServiceException(final String message) {
        super(message);
    }
    public ShippingServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
