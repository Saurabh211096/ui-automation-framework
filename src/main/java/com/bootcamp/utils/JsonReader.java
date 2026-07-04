package com.bootcamp.utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class JsonReader {

	public static Object[][] getJsonData(String filePath) {
		try {
			ObjectMapper mapper = new ObjectMapper();
		
		List<Map<String, String>> dataList = mapper.readValue(
				new File(filePath), 
				new TypeReference<List<Map<String, String>>>() {}
		);
		
		Object[][] dataArray = new Object[dataList.size()][1];
		
		for (int i = 0; i < dataList.size(); i++) {
			dataArray[i][0] = dataList.get(i);
		}
		return dataArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
