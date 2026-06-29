package com.bootcamp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bootcamp.base.BaseTest;

public class Day5_TestNGAssertionsTest extends BaseTest {

	@Test
	public void testHardAssertion() {
		System.out.println("Starting Hard Assertion Test...");
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		String actualTitle = driver.getTitle();
		String expectedTitle = "OrangeHRM";
		Assert.assertEquals(actualTitle, expectedTitle, "Page title did"
				+ " not match!");
		System.out.println("Hard Assertion passed! Title is correct.");
	}
	@Test
	public void testSoftAssertion() {
		System.out.println("Starting Soft Assertion Test...");
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		SoftAssert softAssert = new SoftAssert();
		System.out.println("Running failing soft assertion...");
		softAssert.assertEquals("Orange", "Apple", "Fruits do not match!");
		System.out.println("The test is still running despite the failure above!");
		System.out.println("Running passing soft assertion...");
		softAssert.assertTrue(5 > 3, "Math is broken!");
		softAssert.assertAll();
	}
	
}
