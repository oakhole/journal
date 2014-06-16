package com.oakhole.common.util;

import java.net.URL;
import java.net.URLClassLoader;

public class DynamicClassLoader extends URLClassLoader {

	private DynamicClassLoader(URL url) {
		super(new URL[] { url }, Thread.currentThread().getContextClassLoader());
	}

	public static Object dynamicCreateNewInstance(String classPath,
			String className) {
		classPath = "file:" + classPath;
		try {
			URL url = new URL(classPath);
			DynamicClassLoader dc = new DynamicClassLoader(url);
			Class<?> c = dc.findClass(className);
			if (c == null) {
				c = Class.forName(className);
			}
			return c.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
}
