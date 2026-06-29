package com.bootcamp.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTest {

	public static void main(String[] args) {

		System.out.println("Starting Selenium test...");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.opencart.com/");
		String pageTitle = driver.getTitle();
		System.out.println("The title of the website is: " + pageTitle);
		driver.quit();
		System.out.println("Test complete!");
		
	}

}
