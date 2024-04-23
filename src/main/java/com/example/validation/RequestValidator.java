package com.example.validation;

import com.example.model.Request;

public class RequestValidator {

    public static boolean validate(Request request) {
        if (request.getDealUniqueId() == null || request.getDealUniqueId().isEmpty()) {
            System.out.println("Deal Unique Id is missing.");
            return false;
        }
        if (request.getFromCurrencyISOCode() == null || request.getFromCurrencyISOCode().isEmpty()) {
            System.out.println("From Currency ISO Code is missing.");
            return false;
        }
        if (request.getToCurrencyISOCode() == null || request.getToCurrencyISOCode().isEmpty()) {
            System.out.println("To Currency ISO Code is missing.");
            return false;
        }
        if (request.getDealTimestamp() == null) {
            System.out.println("Deal Timestamp is missing.");
            return false;
        }
        if (request.getDealAmount() <= 0) {
            System.out.println("Deal Amount is invalid.");
            return false;
        }
        return true;
    }
}
