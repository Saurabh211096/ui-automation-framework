package com.bootcamp.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;

@Listeners(com.bootcamp.listeners.ListenerManager.class)
public class Day10_ListenerTest extends BaseTest {

	@Test
	public void testSuccessfulLogin() {
		getDriver().get("https://opensource-demo.orangehrmlive.com/");
		Assert.assertTrue(true, "This will pass, so no screenshot.");
	}
	
	@Test
	public void testFailingCheckout() {
		getDriver().get("https://opensource-demo.orangehrmlive.com/");
		try {Thread.sleep(5000);} catch (InterruptedException e) {}
		log.info("Intentionally failing the test now...");
		Assert.fail("The checkout button was missing!");
	}
	
}
