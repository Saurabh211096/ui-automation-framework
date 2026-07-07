package com.bootcamp.tests;

import org.testng.annotations.Test;

public class Day13_GitTest {

	@Test
	public void collaborationTest() {
		System.out.println("This is a new test written on my feature branch!");
		
		// Let's pretend I hardcoded a URL here (which is bad practice)
		String url = "https://example.come";
		System.out.println("Navigating to: " + url);
	}
	
}
