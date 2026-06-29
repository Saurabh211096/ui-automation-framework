package com.bootcamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bootcamp.utils.WaitUtils;

public class AdminPage {

	private WebDriver driver;
	
	private By adminMenuLink = By.xpath("//span[text()='Admin']");
	private By addButton = By.cssSelector("button[type='button'][class='oxd-button oxd-button--medium oxd-button--secondary']");
	
	public AdminPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickAdminMenu() {
		WaitUtils.waitForClickable(driver, adminMenuLink, 10).click();
	}
	public void clickAddButton() {
		WaitUtils.waitForClickable(driver, addButton, 10).click();
	}
	
}
