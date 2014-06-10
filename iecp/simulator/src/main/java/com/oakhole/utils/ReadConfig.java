package com.oakhole.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	private static Properties prop;
	private static File configFile;

	static {
		prop = new Properties();
		configFile = new File("application.properties");
		try {
			prop.load(new FileReader(configFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return prop.getProperty(key);
	}
}
