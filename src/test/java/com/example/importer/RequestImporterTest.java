package com.example.importer;

import com.example.model.Request;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestImporterTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
    private static final String DB_USER = "testuser";
    private static final String DB_PASSWORD = "testpassword";

    @Before
    public void setupDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement("TRUNCATE TABLE requests")) {
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testImportRequest() {
        Request request = new Request();
        request.setDealUniqueId("123456");
        request.setFromCurrencyISOCode("USD");
        request.setToCurrencyISOCode("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(100.0);

        RequestImporter.importRequest(request);

        assertTrue(checkIfRequestExists(request.getDealUniqueId()));
    }

    @Test
    public void testDuplicateImportRequest() {
        Request request = new Request();
        request.setDealUniqueId("123456");
        request.setFromCurrencyISOCode("USD");
        request.setToCurrencyISOCode("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(100.0);

        RequestImporter.importRequest(request);
        RequestImporter.importRequest(request);

        assertTrue(checkIfRequestExists(request.getDealUniqueId()));
    }

    private boolean checkIfRequestExists(String dealUniqueId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM requests WHERE dealUniqueId = ?")) {
            stmt.setString(1, dealUniqueId);
            return stmt.executeQuery().next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
