package com.example.importer;

import com.example.model.Request;
import com.example.validation.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class RequestImporter {

    private static final Logger logger = LoggerFactory.getLogger(RequestImporter.class);

    private static Set<String> importedRequests = new HashSet<>();

    public static void importRequest(Request request) {
        if (!importedRequests.contains(request.getDealUniqueId())) {
            if (RequestValidator.validate(request)) {
                if (saveToDatabase(request)) {
                    logger.info("Request imported successfully: {}", request);
                    importedRequests.add(request.getDealUniqueId());
                } else {
                    logger.error("Failed to import request to database: {}", request);
                }
            } else {
                logger.warn("Invalid request: {}", request);
            }
        } else {
            logger.info("Request with Deal Unique Id {} already imported.", request.getDealUniqueId());
        }
    }

    private static boolean saveToDatabase(Request request) {
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String username = "testuser";
        String password = "testpassword";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO requests (dealUniqueId, fromCurrencyISOCode, toCurrencyISOCode, dealTimestamp, dealAmount) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, request.getDealUniqueId());
            statement.setString(2, request.getFromCurrencyISOCode());
            statement.setString(3, request.getToCurrencyISOCode());
            statement.setObject(4, request.getDealTimestamp());
            statement.setDouble(5, request.getDealAmount());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            logger.error("Error saving request to database: {}", ex.getMessage(), ex);
            return false;
        }
    }
}
