package com.accenture.acme.store.shippingservice.impl;

import com.accenture.acme.store.shippingservice.ShippingServiceException;
import com.accenture.acme.store.shippingservice.ZipCodeNotFoundException;
import com.google.gson.Gson;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DummyShippingServiceImplTest {
    @Mock
    private OkHttpClient mockedOkHttpClient;
    @Mock
    private Call mockedCall;
    @Captor
    private ArgumentCaptor<Request> requestArgumentCaptor;

    @Test
    public void shouldCallShippingServicePassingParametersCorrectly() throws IOException, ShippingServiceException,
            ZipCodeNotFoundException {
        // Given
        final String destinationZipCode = "53130330";
        final double weight = 0.100;
        Response mockedResponse = createMockedResponse(200, BigDecimal.ONE);

        // When
        when(mockedCall.execute()).thenReturn(mockedResponse);
        when(mockedOkHttpClient.newCall(any(Request.class))).thenReturn(mockedCall);

        DummyShippingServiceImpl dummyShippingService = new DummyShippingServiceImpl(mockedOkHttpClient);
        dummyShippingService.calculateShippingCost(destinationZipCode, weight);

        // Then
        verify(mockedOkHttpClient, only()).newCall(requestArgumentCaptor.capture());
        verify(mockedCall, only()).execute();

        Request capturedRequest = requestArgumentCaptor.getValue();
        assertThat(capturedRequest.url().url().toString()).isEqualTo(getShippingServiceUrl());
        assertThat(capturedRequest.method()).isEqualTo("POST");

        FormBody formBody = (FormBody) capturedRequest.body();
        assertThat(formBody.name(0)).isEqualTo("destinationZipCode");
        assertThat(formBody.name(1)).isEqualTo("weight");
        assertThat(formBody.value(0)).isEqualTo(destinationZipCode);
        assertThat(formBody.value(1)).isEqualTo(String.valueOf(weight));
    }

    private String getShippingServiceUrl() {
        return DummyShippingServiceImpl.BASE_URL + "/shippingcost";
    }

    private Response createMockedResponse(int code, final BigDecimal shippingCost) {
        return new Response.Builder()
                .request(new Request.Builder().url("http://www").post(new FormBody.Builder().build()).build())
                .protocol(Protocol.HTTP_2)
                .code(code)
                .body(ResponseBody.create(MediaType.parse("application/json"), new Gson().toJson(shippingCost)))
                .build();
    }
}