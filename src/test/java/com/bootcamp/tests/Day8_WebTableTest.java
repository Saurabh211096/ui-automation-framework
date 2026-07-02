package com.bootcamp.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;

public class Day8_WebTableTest extends BaseTest {

	@Test
	public void testDynamicWebTable() {
		
		System.out.println("--- Testing Dynamic Web Tables ---");
		getDriver().get("https://the-internet.herokuapp.com/tables");
		String targetFirstName = "Jason";
		String dynamicXpath = "//table[@id='table1']//td[text()='"+targetFirstName+"']/parent::tr/td[4]";
		System.out.println("Constructed XPath: " + dynamicXpath);
		WebElement dueAmountElement = getDriver().findElement(By.xpath(dynamicXpath));
		String actualAmount = dueAmountElement.getText();
		System.out.println(targetFirstName + " currently owes: " + actualAmount);
		Assert.assertEquals(actualAmount, "$100.00", "The due amount for Jason was incorrect!");
	}
	
}
