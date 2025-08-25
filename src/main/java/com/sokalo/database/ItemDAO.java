package com.sokalo.database;

import com.sokalo.models.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    /**
     * Finds a single Item in the database by its barcode.
     * This will be used by the POS system.
     * @param barcode The barcode to search for.
     * @return An Item object if found, otherwise null.
     */
    public Item findByBarcode(String barcode) {
        String sql = "SELECT * FROM items WHERE barcode = ?";
        Item item = null;

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, barcode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                item = new Item(
                        rs.getInt("itemID"),
                        rs.getString("barcode"),
                        rs.getString("name"),
                        rs.getDouble("costPrice"),
                        rs.getDouble("sellingPrice"),
                        rs.getInt("stockQuantity"),
                        LocalDate.parse(rs.getString("expiryDate"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding item by barcode: " + e.getMessage());
        }
        return item;
    }

    /**
     * Retrieves all items from the database.
     * This will be used to display the inventory list for the Stock Manager.
     * @return A list of all Item objects.
     */
    public List<Item> findAll() {
        String sql = "SELECT * FROM items";
        List<Item> items = new ArrayList<>();

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("itemID"),
                        rs.getString("barcode"),
                        rs.getString("name"),
                        rs.getDouble("costPrice"),
                        rs.getDouble("sellingPrice"),
                        rs.getInt("stockQuantity"),
                        LocalDate.parse(rs.getString("expiryDate"))
                );
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Error finding all items: " + e.getMessage());
        }
        return items;
    }

    /**
     * Adds a new item to the database.
     * @param item The Item object to add.
     */
    public void addItem(Item item) {
        String sql = "INSERT INTO items(barcode, name, costPrice, sellingPrice, stockQuantity, expiryDate) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getBarcode());
            pstmt.setString(2, item.getName());
            pstmt.setDouble(3, item.getCostPrice());
            pstmt.setDouble(4, item.getSellingPrice());
            pstmt.setInt(5, item.getStockQuantity());
            pstmt.setString(6, item.getExpiryDate().toString());
            pstmt.executeUpdate();
            System.out.println("New item added: " + item.getName());
        } catch (SQLException e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    /**
     * Updates the stock quantity of a specific item.
     * This is a critical method used after a sale or for manual adjustments.
     * @param itemID The ID of the item to update.
     * @param newQuantity The new stock quantity.
     */
    public void updateStock(int itemID, int newQuantity) {
        String sql = "UPDATE items SET stockQuantity = ? WHERE itemID = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, itemID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating stock: " + e.getMessage());
        }
    }

}
