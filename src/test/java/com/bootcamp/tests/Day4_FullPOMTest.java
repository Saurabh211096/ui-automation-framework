package com.bootcamp.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;
import com.bootcamp.pages.AdminPage;
import com.bootcamp.pages.DashboardPage;
import com.bootcamp.pages.LoginPage;

//@Listeners(com.bootcamp.listeners.ListenerManager.class)
public class Day4_FullPOMTest extends BaseTest {

	@Test
	public void testAllureReportFlow() {
		getDriver().get("https://opensource-demo.orangehrmlive.com/");
		
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("WRONG_PASSWORD");
		loginPage.clickLogin();
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {}
		
		Assert.assertEquals(getDriver().getTitle(), "Dashboard"
				, "Failed to reach Dashboard!");
	}

}
