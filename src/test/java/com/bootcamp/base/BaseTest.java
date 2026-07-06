package com.bootcamp.base;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.bootcamp.utils.ConfigReader;

import io.qameta.allure.Allure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseTest {
	
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	protected Logger log = LogManager.getLogger(this.getClass());
	
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setupBrowser(@Optional String browser) {
		if (System.getProperty("browser") != null) {
			browser = System.getProperty("browser");
		} else if (browser == null) {
			browser = ConfigReader.getProperty("browser");
		}
		log.info("Thread " + Thread.currentThread().getId() 
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
			log.info("Invalid browser passed: " + browser 
					+ ". Defaulting to Chrome");
			threadLocalDriver.set(new ChromeDriver());
			break;
		}
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts()
		.implicitlyWait(Duration.ofSeconds(5));
	}
	
	public WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
//	@AfterMethod(alwaysRun = true)
//	public void teardownBrowser() {
//		if (getDriver() != null) {
//			getDriver().quit();
//			threadLocalDriver.remove();
//		}
//	}
	
	@AfterMethod(alwaysRun = true)
	public void teardownBrowser(ITestResult result) {
		if (getDriver() != null) {
			if (result.getStatus() == ITestResult.FAILURE) {
				byte[] screenshot = ((TakesScreenshot) getDriver())
						.getScreenshotAs(OutputType.BYTES);
				Allure.addAttachment("Failure Screenshot"
						, new ByteArrayInputStream(screenshot));
			}
			getDriver().quit();
			threadLocalDriver.remove();
		}
	}
}
