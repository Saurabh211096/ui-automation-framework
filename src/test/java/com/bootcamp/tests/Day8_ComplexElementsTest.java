package com.bootcamp.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;

public class Day8_ComplexElementsTest extends BaseTest {

	@Test
	public void testDropdownAndAlerts() {
		
		System.out.println("--- 1. Testing Dropdowns ---");
		getDriver().get("https://the-internet.herokuapp.com/dropdown");
		WebElement dropdownElement = getDriver().findElement(By.id("dropdown"));
		Select select = new Select(dropdownElement);
		select.selectByVisibleText("Option 2");
		System.out.println("Successfully selected Option 2 from dropdown.");
		
		System.out.println("--- 2. Testing JavaScript Alerts ---");
		getDriver().get("https://the-internet.herokuapp.com/javascript_alerts");
		getDriver().findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		String alertText = getDriver().switchTo().alert().getText();
		System.out.println("The alert said: " + alertText);
		getDriver().switchTo().alert().accept();
		
		System.out.println("--- 3. Testing Mouse Hover ---");
		getDriver().get("https://the-internet.herokuapp.com/hovers");
		WebElement profileImage = getDriver().findElement(By.xpath("//div[@id='content']/div/div[1]/img"));
		Actions actions = new Actions(getDriver());
		actions.moveToElement(profileImage).build().perform();
		WebElement hiddenText = getDriver().findElement(By.xpath("(//div[@class='figcaption']/h5)[1]"));
		Assert.assertTrue(hiddenText.isDisplayed(), "Hover failed! Text is not visible.");
		System.out.println("Hover successfull! Hidden text revealed: " + hiddenText.getText());
		
	}
	
}
