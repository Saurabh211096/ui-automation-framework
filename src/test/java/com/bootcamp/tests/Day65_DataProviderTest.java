package com.bootcamp.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;

public class Day65_DataProviderTest extends BaseTest {

	@DataProvider(name = "massiveData", parallel = true)
	public Object[][] getMassiveData() {
		Object[][] data = new Object[20][1];
		for (int i = 0; i < 20; i++) {
			data[i][0] = "User" + (i + 1);
		}
		return data;
	}
	
	@Test(dataProvider = "massiveData")
	public void isolationAndMemoryTest(String baseUser) {
		long timestamp = System.currentTimeMillis();
		String uniqueEmail = baseUser + "_" + timestamp 
				+ "@testcompany.com";
		System.out.println("Thread " + Thread.currentThread().getId() 
				+ " is using UNIQUE email: " + uniqueEmail);
		getDriver().get("https://opensource-demo.orangehrmlive.com/");
		Assert.assertTrue(getDriver().getTitle().contains("OrangeHRM"));
	}
	
}
