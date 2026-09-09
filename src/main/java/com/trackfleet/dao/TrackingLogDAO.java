package com.trackfleet.dao;
import com.trackfleet.models.TrackingLog;
import com.trackfleet.database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList; import java.util.List;
public class TrackingLogDAO {
    public void addLog(int parcelId, int branchId, String status, String remarks) {
        String sql = "INSERT INTO tracking_logs (parcel_id, branch_id, status, remarks) VALUES (?,?,?,?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, parcelId); stmt.setInt(2, branchId); stmt.setString(3, status); stmt.setString(4, remarks);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public List<TrackingLog> getLogsForParcel(int parcelId) {
        List<TrackingLog> list = new ArrayList<>();
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tracking_logs WHERE parcel_id = ? ORDER BY timestamp DESC")) {
            stmt.setInt(1, parcelId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                list.add(new TrackingLog(rs.getInt("id"), rs.getInt("parcel_id"), rs.getInt("branch_id"), rs.getString("status"), rs.getString("timestamp"), rs.getString("remarks")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}