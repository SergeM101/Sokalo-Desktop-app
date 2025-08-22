package com.sokalo.models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private int saleID;
    private LocalDateTime timestamp;
    private double totalAmount;
    private String paymentMethod;
    private List<SaleItem> saleItems; // To hold the items in this sale

    // Constructor
    public Sale(int saleID, LocalDateTime timestamp, String paymentMethod) {
        this.saleID = saleID;
        this.timestamp = timestamp;
        this.paymentMethod = paymentMethod;
        this.totalAmount = 0.0;
        this.saleItems = new ArrayList<>();
    }

    // Method to add an item and update total
    public void addSaleItem(SaleItem item) {
        this.saleItems.add(item);
        this.totalAmount += item.getPriceAtTimeOfSale() * item.getQuantitySold();
    }

    // Getters and Setters
    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }
}
