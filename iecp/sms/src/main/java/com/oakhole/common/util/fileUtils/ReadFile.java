package com.oakhole.common.util.fileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ȡ�ļ���������
 * 
 * @author Administrator
 * 
 */
public class ReadFile {

	public ReadFile() {
	}

	/**
	 * �������
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<String> getPhoneNumber(File file) throws Exception {

		List<String> phones = new ArrayList<String>();

		BufferedReader br = null;
		if (file != null && file.exists()) {
			br = new BufferedReader(new FileReader(file));

			String tmp;

			while ((tmp = br.readLine()) != null) {
				if (tmp.matches("\\d+")) {
					phones.add(tmp);
				}
			}

			return phones;
		}
		return null;
	}
}
