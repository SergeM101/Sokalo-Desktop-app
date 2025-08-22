package com.sokalo.models;

public class StaffMember {
    private int staffID;
    private String fullName;
    private String PIN; // This would be hashed in a real application
    private String role;

    // Constructor
    public StaffMember(int staffID, String fullName, String PIN, String role) {
        this.staffID = staffID;
        this.fullName = fullName;
        this.PIN = PIN;
        this.role = role;
    }

    // Getters and Setters
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
