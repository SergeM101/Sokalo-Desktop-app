package com.sokalo.models;

import java.time.LocalDateTime;

public class ShiftLog {
    private int logID;
    private int staffID; // Foreign key to StaffMember
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double cashDiscrepancy;
    private String stockDiscrepancyNotes;

    // Constructor
    public ShiftLog(int logID, int staffID, LocalDateTime startTime, LocalDateTime endTime, double cashDiscrepancy, String stockDiscrepancyNotes) {
        this.logID = logID;
        this.staffID = staffID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cashDiscrepancy = cashDiscrepancy;
        this.stockDiscrepancyNotes = stockDiscrepancyNotes;
    }

    // Getters and Setters
    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getCashDiscrepancy() {
        return cashDiscrepancy;
    }

    public void setCashDiscrepancy(double cashDiscrepancy) {
        this.cashDiscrepancy = cashDiscrepancy;
    }

    public String getStockDiscrepancyNotes() {
        return stockDiscrepancyNotes;
    }

    public void setStockDiscrepancyNotes(String stockDiscrepancyNotes) {
        this.stockDiscrepancyNotes = stockDiscrepancyNotes;
    }
}
