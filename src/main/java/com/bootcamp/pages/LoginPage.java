package com.bootcamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bootcamp.utils.WaitUtils;

public class LoginPage {

	private WebDriver driver;
	
	private By usernameField = By.name("username");
	private By passwordField = By.name("password");
	private By loginButton = By.cssSelector("button[type='submit']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterUsername(String user) {
		WaitUtils.waitForClickable(driver, usernameField, 10).sendKeys(user);
	}
	public void enterPassword(String pass) {
		WaitUtils.waitForClickable(driver, passwordField, 10).sendKeys(pass);
	}
	public void clickLogin() {
		WaitUtils.waitForClickable(driver, loginButton, 10).click();
	}
	
}
