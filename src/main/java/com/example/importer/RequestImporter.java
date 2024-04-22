package com.example.importer;

import com.example.model.Request;
import com.example.validation.RequestValidator;

public class RequestImporter {

    public static void importRequest(Request request) {
        if (RequestValidator.validate(request)) {
            // Save request to database
            System.out.println("Request imported successfully: " + request);
        } else {
            System.out.println("Invalid request: " + request);
        }
    }
}
