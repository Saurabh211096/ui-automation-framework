package com.bootcamp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;
import com.bootcamp.utils.ConfigReader;

public class Day6_ParallelTest extends BaseTest {

	@Test
	public void testDynamicUrl() {
		String targetUrl = ConfigReader.getProperty("baseUrl");
		System.out.println("Navigating dynamically to: " + targetUrl);
		getDriver().get(targetUrl);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testOrangeHRM() {
		getDriver().get("https://opensource-demo.orangehrmlive.com/");
		System.out.println("OrangeHRM Test completed on Thread: " 
				+ Thread.currentThread().getId());
		try {Thread.sleep(5000);} catch (InterruptedException e) {
			e.printStackTrace();}
		Assert.assertTrue(getDriver().getTitle().contains("OrangeHRM"));
	}
	@Test
	public void testOpenCart() {
		getDriver().get("https://demo.opencart.com/");
		System.out.println("OpenCart Test completed on Thread: "
				+ Thread.currentThread().getId());
		Assert.assertNotNull(getDriver().getTitle());
	}
	
}
