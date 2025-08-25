package com.sokalo.database;

import com.sokalo.models.ShiftLog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ShiftLogDAO {

    /**
     * Saves a ShiftLog record to the database at the end of a shift.
     * @param log The ShiftLog object to save.
     */
    public void saveShiftLog(ShiftLog log) {
        String sql = "INSERT INTO shift_logs(staffID, startTime, endTime, cashDiscrepancy, stockDiscrepancyNotes) VALUES(?,?,?,?,?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, log.getStaffID());
            pstmt.setTimestamp(2, Timestamp.valueOf(log.getStartTime()));
            pstmt.setTimestamp(3, Timestamp.valueOf(log.getEndTime()));
            pstmt.setDouble(4, log.getCashDiscrepancy());
            pstmt.setString(5, log.getStockDiscrepancyNotes());

            pstmt.executeUpdate();
            System.out.println("Shift log for staff member " + log.getStaffID() + " has been saved.");

        } catch (SQLException e) {
            System.out.println("Error saving shift log: " + e.getMessage());
        }
    }

}
