package com.trackfleet.dao;
import com.trackfleet.models.Parcel;
import com.trackfleet.database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList; import java.util.List;
public class ParcelDAO {
    public void save(Parcel p) {
        String sql = "INSERT INTO parcels (tracking_number, sender_id, receiver_id, origin_branch_id, dest_branch_id, weight, price, status) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getTrackingNumber()); stmt.setInt(2, p.getSenderId()); stmt.setInt(3, p.getReceiverId());
            stmt.setInt(4, p.getOriginBranchId()); stmt.setInt(5, p.getDestBranchId()); stmt.setDouble(6, p.getWeight());
            stmt.setDouble(7, p.getPrice()); stmt.setString(8, p.getStatus());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) p.setId(rs.getInt(1));
        } catch (Exception e) { e.printStackTrace(); }
    }
    public Parcel get(int id) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM parcels WHERE id = ?")) {
            stmt.setInt(1, id); ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Parcel p = new Parcel();
                p.setId(rs.getInt("id")); p.setTrackingNumber(rs.getString("tracking_number")); p.setSenderId(rs.getInt("sender_id"));
                p.setReceiverId(rs.getInt("receiver_id")); p.setOriginBranchId(rs.getInt("origin_branch_id"));
                p.setDestBranchId(rs.getInt("dest_branch_id")); p.setWeight(rs.getDouble("weight"));
                p.setPrice(rs.getDouble("price")); p.setStatus(rs.getString("status"));
                return p;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    public List<Parcel> getAll() {
        List<Parcel> list = new ArrayList<>();
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM parcels")) {
            while (rs.next()) {
                Parcel p = new Parcel();
                p.setId(rs.getInt("id")); p.setTrackingNumber(rs.getString("tracking_number")); p.setSenderId(rs.getInt("sender_id"));
                p.setReceiverId(rs.getInt("receiver_id")); p.setOriginBranchId(rs.getInt("origin_branch_id"));
                p.setDestBranchId(rs.getInt("dest_branch_id")); p.setWeight(rs.getDouble("weight"));
                p.setPrice(rs.getDouble("price")); p.setStatus(rs.getString("status"));
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    public void update(Parcel p) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE parcels SET sender_id=?, receiver_id=?, origin_branch_id=?, dest_branch_id=?, weight=?, price=?, status=? WHERE id=?")) {
            stmt.setInt(1, p.getSenderId()); stmt.setInt(2, p.getReceiverId()); stmt.setInt(3, p.getOriginBranchId());
            stmt.setInt(4, p.getDestBranchId()); stmt.setDouble(5, p.getWeight()); stmt.setDouble(6, p.getPrice());
            stmt.setString(7, p.getStatus()); stmt.setInt(8, p.getId());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public void delete(int id) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM parcels WHERE id=?")) {
            stmt.setInt(1, id); stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public Parcel findByTrackingNumber(String tn) {
        String sql = "SELECT * FROM parcels WHERE tracking_number = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Parcel p = new Parcel();
                p.setId(rs.getInt("id")); p.setTrackingNumber(tn); p.setSenderId(rs.getInt("sender_id"));
                p.setReceiverId(rs.getInt("receiver_id")); p.setOriginBranchId(rs.getInt("origin_branch_id"));
                p.setDestBranchId(rs.getInt("dest_branch_id")); p.setWeight(rs.getDouble("weight"));
                p.setPrice(rs.getDouble("price")); p.setStatus(rs.getString("status"));
                return p;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    public void updateStatus(int id, String status) {
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE parcels SET status = ? WHERE id = ?")) {
            stmt.setString(1, status); stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}