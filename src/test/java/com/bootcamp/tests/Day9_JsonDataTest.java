package com.bootcamp.tests;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;
import com.bootcamp.utils.JsonReader;

public class Day9_JsonDataTest extends BaseTest {

	@DataProvider(name = "jsonData")
	public Object[][] getJsonData() {
		return JsonReader.getJsonData("src/test/resources/testdata.json");
	}
	
	@Test(dataProvider = "jsonData")
	public void loginTestFromJson(Map<String, String> testData) {
		String username = testData.get("username");
		String password = testData.get("password");
		String isValid = testData.get("isValid");
		
		System.out.println("Thread " + Thread.currentThread().getId()
				+ " | Testing user: " + username
				+ " | Password: " + password
				+ " | Should login succeed?: " + isValid);
	}
	
}
