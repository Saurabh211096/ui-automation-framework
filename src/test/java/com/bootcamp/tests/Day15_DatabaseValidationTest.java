package com.bootcamp.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;
import com.bootcamp.pages.LoginPage;
import com.bootcamp.utils.DbUtils;

public class Day15_DatabaseValidationTest extends BaseTest {

	@Test(priority = 1)
	public void testUserCreationAndDbValidation() {
		// --- PHASE 1: UI AUTOMATION ---
		log.info("Starting UI phase of the test...");
		getDriver().get("https://opensource-demo.orangehrmlive.com/");

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLogin();
		log.info("UI Phase: Successfully logged into OrangeHRM and navigated to Dashboard.");

		// --- PHASE 2: DATABASE VALIDATION ---
		log.info("Starting DB phase of the test...");

		// Define test data with timestamp to ensure uniqueness and test isolation
		String uniqueUsername = "user_" + System.currentTimeMillis();
		String uniqueEmail = uniqueUsername + "@example.com";

		// SQL query templates
		String insertSql = "INSERT INTO users (username, email, status) VALUES (?, ?, ?)";
		String selectSql = "SELECT * FROM users WHERE email = ?";
		String deleteSql = "DELETE FROM users WHERE username = ?";

		// Establishing database connection and executing queries using
		// try-with-resources.
		// This ensures the connection, statements, and result sets are closed
		// automatically, preventing resource leaks.
		try (Connection conn = DbUtils.getConnection()) {

			log.info("DB Phase: Successfully connected to local MySQL database.");

			// 1. Insert user (simulating the database update after a UI registration)
			try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
				insertStmt.setString(1, uniqueUsername);
				insertStmt.setString(2, uniqueEmail);
				insertStmt.setString(3, "active");

				int rowsInserted = insertStmt.executeUpdate();
				log.info("DB Phase: Inserted mock user. Rows affected: " + rowsInserted);
				Assert.assertEquals(rowsInserted, 1, "Failed to insert user into database.");
			}

			// 2. Query database to verify the user exists
			try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
				selectStmt.setString(1, uniqueEmail);

				try (ResultSet rs = selectStmt.executeQuery()) {
					Assert.assertTrue(rs.next(), "User was not found in the database!");

					String dbUsername = rs.getString("username");
					String dbStatus = rs.getString("status");

					log.info("DB Phase: Verified user in DB. Username: " + dbUsername + ", Status: " + dbStatus);
					Assert.assertEquals(dbUsername, uniqueUsername, "Username mismatch in DB.");
					Assert.assertEquals(dbStatus, "active", "User status mismatch in DB.");
				}
			}

			// 3. Clean up data (Tear down) to maintain database independence and
			// cleanliness
			try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
				deleteStmt.setString(1, uniqueUsername);
				int rowsDeleted = deleteStmt.executeUpdate();
				log.info("DB Phase: Cleaned up mock user. Rows affected: " + rowsDeleted);
				Assert.assertEquals(rowsDeleted, 1, "Failed to clean up test user from database.");
			}

		} catch (SQLException e) {
			log.error("DB Phase: Exception occurred during database validation!", e);
			Assert.fail("Database query failed due to SQLException: " + e.getMessage());
		}
	}

	@Test(priority = 2)
	public void testOrderCreationAndDbValidation() {
		log.info("Starting Second Test: Order DB validation...");
		
		// SQL queries for Order verification
        // user_id = 1 belongs to 'saurabh_test' in our database
		int testUserId = 1;
		double testAmount = 550.75;

		String insertOrderSql = "INSERT INTO orders (user_id, amount) VALUES (?, ?)";
		String selectOrderSql = "SELECT * FROM orders WHERE user_id = ? AND amount =?";
		String deleteOrderSql = "DELETE FROM orders WHERE user_id = ? AND amount = ?";

		// Establish connection via JDBC
		try (Connection conn = DbUtils.getConnection()) {
			log.info("DB Phase: Connected to database for order test.");
			// 1. Insert a mock order
			try (PreparedStatement insertStmt = conn.prepareStatement(insertOrderSql)) {
				insertStmt.setInt(1, testUserId);
				insertStmt.setDouble(2, testAmount);

				int rowsInserted = insertStmt.executeUpdate();
				log.info("DB Phase: Inserted mock order. Rows affected: " + rowsInserted);
				Assert.assertEquals(rowsInserted, 1, "Failed to insert order into database.");
			}
			// 2. Query and verify the order exists
			try (PreparedStatement selectStmt = conn.prepareStatement(selectOrderSql)) {
				selectStmt.setInt(1, testUserId);
				selectStmt.setDouble(2, testAmount);

				try (ResultSet rs = selectStmt.executeQuery()) {
					Assert.assertTrue(rs.next(), "Order details were not found in the database!");

					int dbOrderId = rs.getInt("order_id");
					double dbAmount = rs.getDouble("amount");

					log.info("DB Phase: Verified order in DB. Order ID: " + dbOrderId + ", Amount: " + dbAmount);
					Assert.assertEquals(dbAmount, testAmount, "Order amount mismatch in DB.");
				}
			}
			// 3. Clean up order from DB
			try (PreparedStatement deleteStmt = conn.prepareStatement(deleteOrderSql)) {
				deleteStmt.setInt(1, testUserId);
				deleteStmt.setDouble(2, testAmount);

				int rowsDeleted = deleteStmt.executeUpdate();
				log.info("DB Phase: Cleaned up mock order. Rows affected: " + rowsDeleted);
				Assert.assertEquals(rowsDeleted, 1, "Failed to clean up test order from database.");
			}
		} catch (SQLException e) {
			log.error("DB Phase: Exception occurred during order validation!", e);
			Assert.fail("Order database validation failed due to SQLException: " + e.getMessage());
		}
	}
}
