package com.bootcamp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;

public class Day6_GroupingTest extends BaseTest {

	@Test(groups = "smoke")
	public void loginTest() {
		System.out.println("Executing Login Test (SMOKE)...");
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Assert.assertTrue(true);
	}
	@Test(groups = "smoke")
	public void addToCartTest() {
		System.out.println("Executing Add To Cart Test (SMOKE)...");
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Assert.assertTrue(true);
	}
	@Test(groups = "regression")
	public void verifyDiscountCodeTest() {
		System.out.println("Executing Discount Code Test (REGRESSION)...");
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Assert.assertTrue(true);
	}
	
}
