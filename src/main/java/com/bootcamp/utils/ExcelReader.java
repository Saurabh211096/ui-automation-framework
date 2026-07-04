package com.bootcamp.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static Object[][] getExcelData(String filePath, String sheetName) {

		List<Map<String, String>> dataList = new ArrayList<>();

		try {
			FileInputStream file = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheet(sheetName);

			Row headerRow = sheet.getRow(0);
			int totalColumns = headerRow.getLastCellNum();
			int totalRows = sheet.getLastRowNum();

			for (int i = 1; i <= totalRows; i++) {
				Row currentRow = sheet.getRow(i);
				Map<String, String> rowMap = new LinkedHashMap<>();
				for (int j = 0; j < totalColumns; j++) {
					String header = headerRow.getCell(j).getStringCellValue();
					String value = currentRow.getCell(j).getStringCellValue();
					rowMap.put(header, value);
				}
				dataList.add(rowMap);
			}
			workbook.close();
			file.close();
		} catch (Exception e) {
			System.out.println("Failed to read Excel file!");
			e.printStackTrace();
		}
		
		Object[][] dataArray = new Object[dataList.size()][1];
		for (int i = 0; i < dataList.size(); i++) {
			dataArray[i][0] = dataList.get(i);
		}
		return dataArray;
	}

}
