package com.trackfleet.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DESIGN PATTERN: Singleton
public class DatabaseManager {
    private static DatabaseManager instance;
    private static final String URL = "jdbc:sqlite:trackfleet.db";

    private DatabaseManager() {
        // Private constructor for Singleton
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    // Instead of holding one permanent connection that gets accidentally closed,
    // we dispense a new connection each time so DAOs can safely close them.
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
