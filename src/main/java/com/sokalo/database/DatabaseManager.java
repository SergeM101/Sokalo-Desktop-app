package com.sokalo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    // This is the "address" of our local database file.
    // SQLite will automatically create this file in the project's root folder if it doesn't exist.
    private static final String DB_URL = "jdbc:sqlite:sokalo_local.db";

    // We only want one single connection to the database for the whole application.
    private static Connection conn = null;

    // Private constructor to prevent anyone from creating a new instance.
    private DatabaseManager() {
    }

    /**
     * Establishes a connection to the SQLite database if one doesn't already exist.
     * @return The active database connection.
     */
    public static Connection connect() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(DB_URL);
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }

    public static void initializeDatabase() {
        String sqlStaffMembers = "CREATE TABLE IF NOT EXISTS staff_members ("
                + " staffID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " fullName TEXT NOT NULL,"
                + " PIN TEXT NOT NULL,"
                + " role TEXT NOT NULL"
                + ");";

        String sqlItems = "CREATE TABLE IF NOT EXISTS items ("
                + " itemID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " barcode TEXT NOT NULL UNIQUE,"
                + " name TEXT NOT NULL,"
                + " costPrice REAL NOT NULL,"
                + " sellingPrice REAL NOT NULL,"
                + " stockQuantity INTEGER NOT NULL,"
                + " expiryDate TEXT"
                + ");";

        // Add CREATE TABLE statements for Sale, SaleItem, ShiftLog, etc. here

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlStaffMembers);
            stmt.execute(sqlItems);
            System.out.println("Database tables created or already exist.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes the connection to the database.
     */
    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                conn = null; // Set to null so a new connection can be made later
                System.out.println("Connection to SQLite has been closed.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
