package com.bootcamp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;

public class Day6_ParallelTest extends BaseTest {

	@Test
	public void testGoogle() {
		getDriver().get("https://www.google.com");
		System.out.println("Google Test completed on Thread: "
				+ Thread.currentThread().getId());
		Assert.assertTrue(getDriver().getTitle().contains("Google"));
	}
	@Test
	public void testOrangeHRM() {
		getDriver().get("https://opensource-demo.orangehrmlive.com/");
		System.out.println("OrangeHRM Test completed on Thread: " 
				+ Thread.currentThread().getId());
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
