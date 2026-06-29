package com.bootcamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bootcamp.utils.WaitUtils;

public class DashboardPage {

	private WebDriver driver;
	
	private By headerTitle = By.xpath("//h6[text()='Dashboard']");
	private By userDropdown = By.className("oxd-userdropdown-name");
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getDashboardHeaderText() {
		return WaitUtils.waitForVisibility(driver, headerTitle
				, 10).getText();
	}
	public void clickUserDropdown() {
		WaitUtils.waitForClickable(driver, userDropdown, 10).click();
	}
}
