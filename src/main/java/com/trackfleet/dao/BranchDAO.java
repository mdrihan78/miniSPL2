package com.trackfleet.dao;
import com.trackfleet.models.Branch;
import com.trackfleet.database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList; import java.util.List;
public class BranchDAO {
    public void add(Branch b) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO branches (name, address, phone) VALUES (?, ?, ?)")) {
            stmt.setString(1, b.getName()); stmt.setString(2, b.getAddress()); stmt.setString(3, b.getPhone());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public Branch get(int id) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM branches WHERE id = ?")) {
            stmt.setInt(1, id); ResultSet rs = stmt.executeQuery();
            if (rs.next()) return new Branch(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("phone"));
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    public List<Branch> getAll() {
        List<Branch> list = new ArrayList<>();
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM branches")) {
            while (rs.next()) list.add(new Branch(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("phone")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    public void update(Branch b) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE branches SET name=?, address=?, phone=? WHERE id=?")) {
            stmt.setString(1, b.getName()); stmt.setString(2, b.getAddress()); stmt.setString(3, b.getPhone()); stmt.setInt(4, b.getId());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public void delete(int id) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM branches WHERE id=?")) {
            stmt.setInt(1, id); stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}