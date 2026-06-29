package com.bootcamp.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;
	
	@BeforeMethod
	public void setupBrowser() {
		System.out.println("BaseTest: Opening Chrome Browser...");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	@AfterMethod
	public void teardownBrowser() {
		System.out.println("BaseTest: Closing Browser...");
		if (driver != null) {
			driver.quit();
		}
	}
	
}
