package com.bootcamp.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;

public class Day8_ModernDOMTest extends BaseTest {

	@Test
	public void testShadowDOM() {	
		System.out.println("--- Testing Shadow DOM ---");
		getDriver().get("https://the-internet.herokuapp.com/shadowdom");
		WebElement shadowHost = getDriver().findElement(By.xpath("//my-paragraph[1]"));
		SearchContext shadowRoot = shadowHost.getShadowRoot();
		WebElement hiddenText = shadowHost.findElement(By.cssSelector("span"));
		System.out.println("Successfully pierced the Shadow DOM! The text inside is: "
				+ hiddenText.getText());
	}
	@Test
	public void testSVGElements() {
		System.out.println("--- Testing SVG Elements ---");
		getDriver().get("https://opensource-demo.orangehrmlive.com/");
		String svgXpath = "//*[local-name()='svg']";
		int svgCount = getDriver().findElements(By.xpath(svgXpath)).size();
		System.out.println("Found " + svgCount + " SVG icons on the page using local-name()!");
	}
	
}
