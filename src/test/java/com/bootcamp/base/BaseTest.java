package com.bootcamp.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

//	protected WebDriver driver;
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	
	@BeforeMethod(alwaysRun = true)
	public void setupBrowser() {
		System.out.println("Thread ID: " + Thread.currentThread().getId()
				+ " - Opening Browser");
		threadLocalDriver.set(new ChromeDriver());
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	public WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardownBrowser() {
		System.out.println("Thread ID: " + Thread.currentThread().getId()
				+ " - Closing Browser");
		if (getDriver() != null) {
			getDriver().quit();
			threadLocalDriver.remove();
		}
	}
	
}
