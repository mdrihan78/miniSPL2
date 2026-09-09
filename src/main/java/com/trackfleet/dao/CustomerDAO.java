package com.trackfleet.dao;
import com.trackfleet.models.Customer;
import com.trackfleet.database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList; import java.util.List;
public class CustomerDAO {
    public void add(Customer c) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)")) {
            stmt.setString(1, c.getName()); stmt.setString(2, c.getEmail()); stmt.setString(3, c.getPhone());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public Customer get(int id) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE id = ?")) {
            stmt.setInt(1, id); ResultSet rs = stmt.executeQuery();
            if (rs.next()) return new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone"));
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {
            while (rs.next()) list.add(new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    public void update(Customer c) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET name=?, email=?, phone=? WHERE id=?")) {
            stmt.setString(1, c.getName()); stmt.setString(2, c.getEmail()); stmt.setString(3, c.getPhone()); stmt.setInt(4, c.getId());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public void delete(int id) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM customers WHERE id=?")) {
            stmt.setInt(1, id); stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}