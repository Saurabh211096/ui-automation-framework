package com.bootcamp.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	public static ExtentReports getReporter() {
		if (extent == null) {
			String reportPath = System.getProperty("user.dir") 
					+ "/target/ExtentReport.html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("Bootcamp Automation Report");
			spark.config().setReportName("UI Test Execution Results");
			
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("QA Engineer", "Saurabh");
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;
	}
	
	public static ExtentTest getTest() {
		return extentTest.get();
	}
	
	public static void setTest(ExtentTest test) {
		extentTest.set(test);
	}
	
	public static void removeTest() {
		extentTest.remove();
	}
	
}
