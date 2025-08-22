package com.sokalo.models;


import java.time.LocalDateTime;

public class InventoryAdjustment {
    private int adjustmentID;
    private int itemID; // Foreign key to Item
    private int staffID; // Foreign key to StaffMember
    private int quantityChanged;
    private String reason;
    private LocalDateTime timestamp;

    // Constructor
    public InventoryAdjustment(int adjustmentID, int itemID, int staffID, int quantityChanged, String reason, LocalDateTime timestamp) {
        this.adjustmentID = adjustmentID;
        this.itemID = itemID;
        this.staffID = staffID;
        this.quantityChanged = quantityChanged;
        this.reason = reason;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getAdjustmentID() {
        return adjustmentID;
    }

    public void setAdjustmentID(int adjustmentID) {
        this.adjustmentID = adjustmentID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getQuantityChanged() {
        return quantityChanged;
    }

    public void setQuantityChanged(int quantityChanged) {
        this.quantityChanged = quantityChanged;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
