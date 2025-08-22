package com.sokalo.models;

public class SaleItem {
    private int saleItemID;
    private int saleID; // Foreign key to Sale
    private int itemID; // Foreign key to Item
    private int quantitySold;
    private double priceAtTimeOfSale;

    // Constructor
    public SaleItem(int saleItemID, int saleID, int itemID, int quantitySold, double priceAtTimeOfSale) {
        this.saleItemID = saleItemID;
        this.saleID = saleID;
        this.itemID = itemID;
        this.quantitySold = quantitySold;
        this.priceAtTimeOfSale = priceAtTimeOfSale;
    }

    // Getters and Setters
    public int getSaleItemID() {
        return saleItemID;
    }

    public void setSaleItemID(int saleItemID) {
        this.saleItemID = saleItemID;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getPriceAtTimeOfSale() {
        return priceAtTimeOfSale;
    }

    public void setPriceAtTimeOfSale(double priceAtTimeOfSale) {
        this.priceAtTimeOfSale = priceAtTimeOfSale;
    }
}
