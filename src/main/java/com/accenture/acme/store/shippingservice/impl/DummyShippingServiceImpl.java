package com.accenture.acme.store.shippingservice.impl;

import com.accenture.acme.store.shippingservice.ShippingService;
import com.accenture.acme.store.shippingservice.ShippingServiceException;
import com.accenture.acme.store.shippingservice.ZipCodeNotFoundException;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.math.BigDecimal;

class DummyShippingServiceImpl implements ShippingService {
    public static final String BASE_URL = "http://www.dummyshipping.com/rest";
    private final OkHttpClient okHttpClient;
    private final Gson gson = new Gson();

    public DummyShippingServiceImpl(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public BigDecimal calculateShippingCost(final String destinationZipCode, final double weight)
            throws ShippingServiceException, ZipCodeNotFoundException {
        BigDecimal shippingCost;

        RequestBody formBody = new FormBody.Builder()
                .add("destinationZipCode", destinationZipCode)
                .add("weight", String.valueOf(weight))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/shippingcost")
                .post(formBody)
                .build();

        Call call = this.okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            if (response.code() == 200) {
                shippingCost = this.gson.fromJson(response.body().string(), BigDecimal.class);
            } else if (response.code() == 404) {
                throw new ZipCodeNotFoundException("Zipcode not found");
            } else {
                throw new ShippingServiceException("Service unexpected error");
            }
        } catch (IOException e) {
            throw new ShippingServiceException("Communication failure", e);
        }

        return shippingCost;
    }
}
