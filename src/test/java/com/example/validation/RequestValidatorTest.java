package com.example.validation;

import com.example.model.Request;
import org.junit.Test;
import static org.junit.Assert.*;

public class RequestValidatorTest {

    @Test
    public void testValidRequest() {
        Request validRequest = new Request();
        // Set valid request fields
        assertTrue(RequestValidator.validate(validRequest));
    }

    @Test
    public void testInvalidRequest() {
        Request invalidRequest = new Request();
        // Set invalid request fields
        assertFalse(RequestValidator.validate(invalidRequest));
    }
}
