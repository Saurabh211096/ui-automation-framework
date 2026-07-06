package com.bootcamp.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.bootcamp.base.BaseTest;
import com.bootcamp.utils.ExtentManager;

import io.qameta.allure.Attachment;

public class ListenerManager implements ITestListener {

	private ExtentReports extent = ExtentManager.getReporter();
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod()
				.getMethodName());
		ExtentManager.setTest(test);
		ExtentManager.getTest().log(Status.INFO, "Test Execution Started");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getTest().log(Status.PASS, "Test Passed Successfully");
		ExtentManager.removeTest();
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentManager.getTest().log(Status.FAIL, "Test Failed!");
		ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
		
		try {
			Object testClass = result.getInstance();
			WebDriver driver = ((BaseTest) testClass).getDriver();
			
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());
			String fileName = result.getMethod().getMethodName() 
					+ "_" + timestamp;
			File destinationPath = new File(System.getProperty("user.dir") 
					+ "/target/screenshots" + fileName + ".png");
			destinationPath.getParentFile().mkdirs();
			File sourceScreenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(sourceScreenshot, destinationPath);
			
			String base64Img = ((TakesScreenshot)driver)
					.getScreenshotAs(OutputType.BASE64);
			ExtentManager.getTest().addScreenCaptureFromBase64String(base64Img
					, "Failure Screenshot");
		} catch (Exception e) {
			System.out.println("Failed to capture screenshot!");
		}
		ExtentManager.removeTest();
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		System.out.println("*** LISTENER: Test Suite Finished."
				+ " ExtentReport Generated! ***");
		
	}
}
