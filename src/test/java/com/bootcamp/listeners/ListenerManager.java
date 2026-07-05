package com.bootcamp.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.bootcamp.base.BaseTest;

public class ListenerManager implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("*** LISTENER: Test Started -> " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("*** LISTENER: Test Passed -> " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("*** LISTENER: TEST FAILED! Taking Screenshot... -> " + result.getMethod().getMethodName());
		try {
			Object testClass = result.getInstance();
			WebDriver driver = ((BaseTest) testClass).getDriver();

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String testName = result.getMethod().getMethodName();
			String fileName = testName + "_" + timestamp;

			File sourceScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(
					System.getProperty("user.dir") + "/target/screenshots/" + fileName + ".png");
			destinationPath.getParentFile().mkdirs();
			FileHandler.copy(sourceScreenshot, destinationPath);
			String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

			System.out.println("*** LISTENER: Screenshot saved at: " + destinationPath.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Failed to save screenshot!");
			e.printStackTrace();
		}
	}

}
