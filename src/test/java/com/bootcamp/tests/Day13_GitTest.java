package com.bootcamp.tests;

import org.testng.annotations.Test;

import com.bootcamp.utils.ConfigReader;

public class Day13_GitTest {

	@Test
	public void collaborationTest() {
		System.out.println("This is a new test written on my feature branch!");
		
		// Fixed: Using ConfigReader instead of hardcoding!
		String url = ConfigReader.getProperty("baseUrl");
		System.out.println("Navigating to: " + url);
	}
	
}
