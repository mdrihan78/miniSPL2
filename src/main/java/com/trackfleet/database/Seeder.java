package com.trackfleet.database;

import java.sql.Connection;
import java.sql.Statement;

public class Seeder {
    public static void initDatabase() {
        String[] tables = {
            "CREATE TABLE IF NOT EXISTS branches (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, phone TEXT)",
            "CREATE TABLE IF NOT EXISTS customers (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, phone TEXT)",
            "CREATE TABLE IF NOT EXISTS parcels (id INTEGER PRIMARY KEY AUTOINCREMENT, tracking_number TEXT UNIQUE, sender_id INTEGER, receiver_id INTEGER, origin_branch_id INTEGER, dest_branch_id INTEGER, weight REAL, price REAL, status TEXT, FOREIGN KEY(sender_id) REFERENCES customers(id), FOREIGN KEY(receiver_id) REFERENCES customers(id))",
            "CREATE TABLE IF NOT EXISTS tracking_logs (id INTEGER PRIMARY KEY AUTOINCREMENT, parcel_id INTEGER, branch_id INTEGER, status TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, remarks TEXT, FOREIGN KEY(parcel_id) REFERENCES parcels(id))"
        };
        
        String[] seedData = {
            "INSERT OR IGNORE INTO branches (id, name, address, phone) VALUES (1, 'Main Hub - NY', '100 Broadway', '555-0101')",
            "INSERT OR IGNORE INTO branches (id, name, address, phone) VALUES (2, 'West Coast Hub - CA', '200 Sunset Blvd', '555-0202')",
            "INSERT OR IGNORE INTO customers (id, name, email, phone) VALUES (1, 'Alice Smith', 'alice@test.com', '555-1111')",
            "INSERT OR IGNORE INTO customers (id, name, email, phone) VALUES (2, 'Bob Johnson', 'bob@test.com', '555-2222')"
        };

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            for (String query : tables) stmt.execute(query);
            for (String query : seedData) stmt.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
