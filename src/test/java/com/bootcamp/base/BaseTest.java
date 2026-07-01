package com.bootcamp.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.bootcamp.utils.ConfigReader;

public class BaseTest {
	
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setupBrowser(@Optional String browser) {
		if (System.getProperty("browser") != null) {
			browser = System.getProperty("browser");
		} else if (browser == null) {
			browser = ConfigReader.getProperty("browser");
		}
		System.out.println("Thread " + Thread.currentThread().getId() 
				+ " - Initializing: " + browser);
		
		browser = browser.toLowerCase();
		switch (browser) {
		case "chrome":
			threadLocalDriver.set(new ChromeDriver());
			break;
		case "firefox":
			threadLocalDriver.set(new FirefoxDriver());
			break;
		case "edge":
			threadLocalDriver.set(new EdgeDriver());
			break;
		default:
			System.out.println("Invalid browser passed: " + browser 
					+ ". Defaulting to Chrome");
			threadLocalDriver.set(new ChromeDriver());
			break;
		}
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	public WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardownBrowser() {
		if (getDriver() != null) {
			getDriver().quit();
			threadLocalDriver.remove();
		}
	}
}
