package com.sokalo.database;

import com.sokalo.models.Sale;
import com.sokalo.models.SaleItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class SaleDAO {

    /**
     * Saves a completed Sale and its associated SaleItems to the database.
     * This is a transactional process to ensure data integrity.
     * @param sale The Sale object to save.
     */
    public void saveSale(Sale sale) {
        String saleSql = "INSERT INTO sales(timestamp, totalAmount, paymentMethod, staffID) VALUES(?,?,?,?)";
        String saleItemSql = "INSERT INTO sale_items(saleID, itemID, quantitySold, priceAtTimeOfSale) VALUES(?,?,?,?)";

        // THE FIX: Use try-with-resources for the connection
        try (Connection conn = DatabaseManager.connect()) {
            // Start a transaction
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtSale = conn.prepareStatement(saleSql, Statement.RETURN_GENERATED_KEYS)) {
                pstmtSale.setTimestamp(1, Timestamp.valueOf(sale.getTimestamp()));
                pstmtSale.setDouble(2, sale.getTotalAmount());
                pstmtSale.setString(3, sale.getPaymentMethod());
                pstmtSale.setInt(4, sale.getStaffID());

                int affectedRows = pstmtSale.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating sale failed, no rows affected.");
                }

                // Get the ID of the newly created Sale
                try (ResultSet generatedKeys = pstmtSale.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int saleId = generatedKeys.getInt(1);

                        // Now, insert all the SaleItems linked to this Sale
                        try (PreparedStatement pstmtSaleItem = conn.prepareStatement(saleItemSql)) {
                            for (SaleItem item : sale.getSaleItems()) {
                                pstmtSaleItem.setInt(1, saleId);
                                pstmtSaleItem.setInt(2, item.getItemID());
                                pstmtSaleItem.setInt(3, item.getQuantitySold());
                                pstmtSaleItem.setDouble(4, item.getPriceAtTimeOfSale());
                                pstmtSaleItem.addBatch();
                            }
                            pstmtSaleItem.executeBatch();
                        }
                    } else {
                        throw new SQLException("Creating sale failed, no ID obtained.");
                    }
                }

                // Commit the transaction
                conn.commit();
                System.out.println("Sale " + sale.getSaleID() + " saved successfully.");

            } catch (SQLException e) {
                System.out.println("Sale save transaction failed: " + e.getMessage());
                // Rollback the transaction on error
                conn.rollback();
            }

        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

}
