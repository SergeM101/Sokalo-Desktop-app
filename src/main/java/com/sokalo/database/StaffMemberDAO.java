package com.sokalo.database;

import com.sokalo.models.StaffMember;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffMemberDAO {

    /**
     * Finds a StaffMember in the database by their PIN.
     * This is used for the login process.
     * @param pin The PIN entered by the user.
     * @return A StaffMember object if found, otherwise null.
     */
    public StaffMember findByPin(String pin) {
        String sql = "SELECT * FROM staff_members WHERE PIN = ?";
        StaffMember staffMember = null;

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pin);
            ResultSet rs = pstmt.executeQuery();

            // Check if we found a result
            if (rs.next()) {
                staffMember = new StaffMember(
                        rs.getInt("staffID"),
                        rs.getString("fullName"),
                        rs.getString("PIN"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return staffMember;
    }

    /**
     * Adds a new StaffMember to the database.
     * In a real app, this would be done by the Store Manager.
     * @param fullName The full name of the staff member.
     * @param pin The PIN for the staff member.
     * @param role The role (e.g., 'Cashier', 'Store Manager').
     */
    public void addStaffMember(String fullName, String pin, String role) {
        String sql = "INSERT INTO staff_members(fullName, PIN, role) VALUES(?,?,?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setString(2, pin); // In a real app, you would hash this PIN
            pstmt.setString(3, role);
            pstmt.executeUpdate();
            System.out.println("New staff member added: " + fullName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
