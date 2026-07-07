package com.bootcamp.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	// THIS IS THE FEATURE BRANCH COMMENT
	private static Properties properties;
	
//	static {
//		try {
//			String filePath = "src/test/resources/config.properties";
//			FileInputStream file = new FileInputStream(filePath);
//			properties = new Properties();
//			properties.load(file);
//			file.close();
//		} catch (Exception e) {
//			System.err.println("Failed to load config.properties file!");
//			e.printStackTrace();
//		}
//	}
	static {
		try {
			String env = System.getProperty("env");
			if (env == null) {
				env = "qa";
			}
			System.out.println("ConfigReader: Loading environment -> "
					+ env.toUpperCase());
			String filePath = "src/test/resources/config." + env
					+ ".properties";
			FileInputStream file = new FileInputStream(filePath);
			properties = new Properties();
			properties.load(file);
			file.close();
		} catch (Exception e) {
			System.err.println("Failed to load properties file!");
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
}
