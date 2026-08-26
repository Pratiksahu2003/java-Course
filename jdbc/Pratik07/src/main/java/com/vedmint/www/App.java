package com.vedmint.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Creates tables, inserts sample data, and fetches rows from the database.
 */
public class App {

    private static final String CREATE_TASK_TYPE = """
            CREATE TABLE IF NOT EXISTS task_type (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL UNIQUE,
                description VARCHAR(255),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """;

    private static final String CREATE_USERS = """
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                email VARCHAR(150) NOT NULL UNIQUE,
                password VARCHAR(255) NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """;

    private static final String INSERT_TASK_TYPE = """
            INSERT IGNORE INTO task_type (name, description) VALUES (?, ?)
            """;

    private static final String INSERT_USER = """
            INSERT IGNORE INTO users (name, email, password) VALUES (?, ?, ?)
            """;

    private static final String SELECT_TASK_TYPES = "SELECT id, name, description, created_at FROM task_type";
    private static final String SELECT_USERS = "SELECT id, name, email, created_at FROM users";

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            System.out.println("Connected to database successfully!");

            stmt.executeUpdate(CREATE_TASK_TYPE);
            System.out.println("Table 'task_type' created (or already exists).");

            stmt.executeUpdate(CREATE_USERS);
            System.out.println("Table 'users' created (or already exists).");

            insertSampleData(conn);
            fetchAndPrint(conn);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertSampleData(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(INSERT_TASK_TYPE)) {
            insertTaskType(ps, "Bug", "Defect or issue that needs fixing");
            insertTaskType(ps, "Feature", "New functionality to implement");
            insertTaskType(ps, "Chore", "Maintenance or housekeeping work");
        }

        try (PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {
            insertUser(ps, "Alice Sharma", "alice@example.com", "pass123");
            insertUser(ps, "Bob Patel", "bob@example.com", "pass456");
            insertUser(ps, "Carol Mehta", "carol@example.com", "pass789");
        }

        System.out.println("Sample data inserted (duplicates ignored).");
    }

    private static void insertTaskType(PreparedStatement ps, String name, String description)
            throws SQLException {
        ps.setString(1, name);
        ps.setString(2, description);
        ps.executeUpdate();
    }

    private static void insertUser(PreparedStatement ps, String name, String email, String password)
            throws SQLException {
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.executeUpdate();
    }

    private static void fetchAndPrint(Connection conn) throws SQLException {
        System.out.println("\n--- Task Types ---");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_TASK_TYPES)) {
            while (rs.next()) {
                System.out.printf("id=%d | name=%s | description=%s | created_at=%s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"));
            }
        }

        System.out.println("\n--- Users ---");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_USERS)) {
            while (rs.next()) {
                System.out.printf("id=%d | name=%s | email=%s | created_at=%s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at"));
            }
        }
    }
}
