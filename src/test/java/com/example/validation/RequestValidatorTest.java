package com.example.validation;

import com.example.model.Request;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestValidatorTest {

    @Test
    public void testValidRequest() {
        Request validRequest = new Request();
        validRequest.setDealUniqueId("123456");
        validRequest.setFromCurrencyISOCode("USD");
        validRequest.setToCurrencyISOCode("EUR");
        validRequest.setDealTimestamp(LocalDateTime.now());
        validRequest.setDealAmount(100.0);
        assertTrue(RequestValidator.validate(validRequest));
    }

    @Test
    public void testInvalidRequest() {
        Request invalidRequest = new Request();
        assertFalse(RequestValidator.validate(invalidRequest));
    }
}
