package com.bootcamp.tests;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bootcamp.base.BaseTest;
import com.bootcamp.utils.ExcelReader;

public class Day9_ExcelDataTest extends BaseTest {

	@DataProvider(name = "excelData")
	public Object[][] getExcelData() {
		return ExcelReader.getExcelData("src/test/resources/testdata1.xlsx"
				, "Sheet1");
	}
	
	@Test(dataProvider = "excelData")
	public void testFromExcel(Map<String, String> testData) {
		String user = testData.get("Username");
		String pass = testData.get("Password");
		String expectedError = testData.get("ExpectedError");
		
		System.out.println("EXCEL TEST -> User: " + user 
				+ " | Error Expected: " + expectedError);
	}
	
}
