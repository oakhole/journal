package com.oakhole.common.util.fileUtils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXml {

	@SuppressWarnings("unchecked")
	public static Map<String, List<Element>> getDataMap(InputStream in,
			Map<String, String> param) {

		Map<String, List<Element>> result = new HashMap<String, List<Element>>();

		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(in);

			for (String key : param.keySet()) {
				result.put(key, doc.selectNodes(param.get(key)));
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) throws FileNotFoundException {
		List<String> list = new ArrayList<String>();
		list.add("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		list.add("<returnsms>");
		list.add("<status>");
		list.add("<mobile>15023239810</mobile>");
		list.add("<status>10</status>");
		list.add("<taskid>1212</taskid>");
		list.add("</status>");
		list.add("</returnsms>");
		StringBuffer sb = new StringBuffer("");
		for (String str : list) {
			sb.append(str + "\n");
		}

		Map<String, String> param = new HashMap<String, String>();
		param.put("status", "//returnsms/status/status");

		byte[] bArray = sb.toString().getBytes();

		System.out.println(new String(bArray));

		Map<String, List<Element>> result = ReadXml.getDataMap(
				new ByteArrayInputStream(bArray), param);

		for (int i = 0; i < result.get("status").size(); i++) {
			System.out.println(((Element) result.get("status").get(i))
					.getData());
		}
	}
}
