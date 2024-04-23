package com.example;

import com.example.importer.RequestImporter;
import com.example.model.Request;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Sample request
        Request request = new Request();
        request.setDealUniqueId("123456");
        request.setFromCurrencyISOCode("USD");
        request.setToCurrencyISOCode("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(100.0);

        // Import request
        RequestImporter.importRequest(request);
    }
}
