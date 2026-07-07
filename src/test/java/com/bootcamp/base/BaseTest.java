package com.bootcamp.base;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
		System.out.println("Thread " + Thread.currentThread().getId() + " - Initializing: " + browser);

		browser = browser.toLowerCase();
		switch (browser) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			if (isHeadless) {
				chromeOptions.addArguments("--headless=new");
				chromeOptions.addArguments("--window-size=1920,1080");
			}
			threadLocalDriver.set(new ChromeDriver(chromeOptions));
			break;
		case "mobile":
			ChromeOptions mobileOptions = new ChromeOptions();
			Map<String, String> deviceMetrics = new HashMap<>();
			deviceMetrics.put("deviceName", "iPhone SE");
			mobileOptions.setExperimentalOption("mobileEmulation", deviceMetrics);
			if (isHeadless) {
				mobileOptions.addArguments("--headless=new");
			}
			threadLocalDriver.set(new ChromeDriver(mobileOptions));
			getDriver().manage().window().setSize(new Dimension(375, 812));
			break;
		case "firefox":
			threadLocalDriver.set(new FirefoxDriver());
			break;
		case "edge":
			threadLocalDriver.set(new EdgeDriver());
			break;
		default:
			threadLocalDriver.set(new ChromeDriver());
			break;
		}
		if (!browser.equals("mobile")) {
			getDriver().manage().window().maximize();
		}
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	@AfterMethod(alwaysRun = true)
	public void teardownBrowser(ITestResult result) {
		if (getDriver() != null) {
			if (result.getStatus() == ITestResult.FAILURE) {
				try {
					byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
					Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
				} catch (Exception e) {
					System.out.println("Failed to attach screenshot to Allure.");
				}
			}
			getDriver().quit();
			threadLocalDriver.remove();
		}
	}
}
