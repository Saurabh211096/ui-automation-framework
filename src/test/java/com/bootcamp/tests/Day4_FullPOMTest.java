package com.bootcamp.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.bootcamp.pages.AdminPage;
import com.bootcamp.pages.DashboardPage;
import com.bootcamp.pages.LoginPage;

public class Day4_FullPOMTest {

	public static void main(String[] args) {

		System.out.println("Starting Full POM E2E Test...");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		// Page 1: Login
		System.out.println("Executing Login...");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLogin();
		// Page 2: Dashboard
		System.out.println("Verifying Dashboard...");
		DashboardPage dashPage = new DashboardPage(driver);
		String headerText = dashPage.getDashboardHeaderText();
		System.out.println("Successfully landed on: " + headerText);
		// Page 3: Admin
		System.out.println("Navigating to Admin and clicking Add...");
		AdminPage adminPage = new AdminPage(driver);
		adminPage.clickAdminMenu();
		adminPage.clickAddButton();
		System.out.println("Test completed successfully! All POM classes work together.");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}
