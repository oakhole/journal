package com.oakhole.common.util.fileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import jxl.Sheet;
import jxl.Workbook;

public class PhoneFileUtils {

	/**
	 * 读取txt文件中的号码,每行一个号码
	 * 
	 * @param file
	 */
	public static Set<String> getAllPhonesFromTxt(File file) {

		Set<String> allPhoneNumber = new HashSet<String>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				if (tmp.matches("\\d{11}") && tmp.startsWith("1")) {
					allPhoneNumber.add(tmp);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return allPhoneNumber;
	}

	/**
	 * 读取excel文件中的号码
	 * 
	 * @return
	 */
	public static Set<String> getAllPhonesFromExcel(File file) {

		Set<String> allPhoneNumber = new HashSet<String>();

		Workbook book = null;
		try {
			book = Workbook.getWorkbook(file);
		} catch (Exception e) {
			return getAllPhonesFromCsv(file);
		}

		if (book != null) {

			int sheetNo = book.getNumberOfSheets();

			for (int i = 0; i < sheetNo; i++) {

				Sheet sheet = book.getSheet(i);
				int rowNum = sheet.getRows();
				int colNum = sheet.getColumns();
				String tmp = "";
				for (int r = 0; r < rowNum; r++) {// 行
					for (int c = 0; c < colNum; c++) {// 列
						tmp = sheet.getCell(c, r).getContents();
						if (tmp.startsWith("1") && tmp.matches("\\d{11}")) {
							allPhoneNumber.add(tmp);
						}
					}
				}
			}
			book.close();
		}

		return allPhoneNumber;
	}

	/**
	 * 读取普通逗号(,)分隔的csv文件
	 * 
	 * @return
	 */
	public static Set<String> getAllPhonesFromCsv(File file) {

		Set<String> allPhoneNumber = new HashSet<String>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				for (String tmpStr : tmp.split(",")) {
					if (tmpStr.startsWith("1") && tmpStr.matches("\\d{11}")) {
						allPhoneNumber.add(tmpStr);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return allPhoneNumber;
	}

	/**
	 * 读取csv文件内容
	 * 
	 * @param csvFile
	 * @return
	 */
	public static List<String> getFileInfoFromCSV(File csvFile) {
		List<String> infoList = null;

		if (csvFile.exists() && csvFile.getName().endsWith(".csv")) {
			infoList = new ArrayList<String>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(csvFile));
				String tmp = null;
				while ((tmp = br.readLine()) != null) {
					infoList.add(tmp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return infoList;
	}

	/**
	 * 导出txt文件
	 * 
	 * @param mobile
	 * @param file
	 * @return
	 */
	public static int write2Txt(String mobile, File file) {
		PrintWriter pw = null;
		int count = 0;
		try {
			pw = new PrintWriter(file);
			for (String phone : mobile.split("\r\n")) {
				if (phone.matches("\\d{11}") && phone.startsWith("1")) {
					pw.append(phone + "\r\n");
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
		return count;
	}

	public static int write2Txt(Set<String> mobile, File file) {
		PrintWriter pw = null;
		FileWriter fw = null;
		int count = 0;
		try {
			fw = new FileWriter(file, true);
			pw = new PrintWriter(fw);
			for (String phone : mobile) {
				if (phone.matches("\\d{11}") && phone.startsWith("1")) {
					pw.print(phone + "\r\n");
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
		return count;
	}

	/**
	 * 导出csv文件
	 * 
	 * @param file
	 * @return
	 */
	public static void write2Csv(File srcFile, File destFile) {

		try {
			if (srcFile.getName().endsWith(".csv")) {
				FileUtils.copyFile(srcFile, destFile);
			} else {
				Workbook book = Workbook.getWorkbook(srcFile);
				if (book != null) {
					FileWriter fw = new FileWriter(destFile, true);
					PrintWriter pw = new PrintWriter(fw);
					int sheetNo = book.getNumberOfSheets();
					for (int i = 0; i < sheetNo; i++) {
						Sheet sheet = book.getSheet(i);
						int rowNum = sheet.getRows();
						int colNum = sheet.getColumns();
						String str = "";
						String tmp = "";
						StringBuffer metadata = null;
						for (int r = 0; r < rowNum; r++) {// 行
							str = sheet.getCell(0, r).getContents();
							if (str.startsWith("1") && str.matches("\\d{11}")) {
								metadata = new StringBuffer();
								metadata.append(str);
								for (int c = 0; c < colNum; c++) {// 列
									tmp = sheet.getCell(c, r).getContents();
									metadata.append("," + tmp);
								}
								pw.println(metadata);
								metadata = new StringBuffer();
							}
						}
					}
					pw.flush();
					book.close();
					pw.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建以当前日期作为目录的文件 eg: prefix/yyyy/MM/dd/suffix
	 * 
	 * @param prefixPath
	 * @param suffixPath
	 * @return
	 */
	public static File getCurrentDateFile(String prefixPath, String suffixPath) {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		String yearPath = prefixPath + File.separator + year;
		String monthPath = yearPath + File.separator
				+ (month < 10 ? "0" + month : month);
		String dayPath = monthPath + File.separator
				+ (day < 10 ? "0" + day : day);
		File rootDir = new File(prefixPath);
		File yearDir = new File(yearPath);
		File monthDir = new File(monthPath);
		File dayDir = new File(dayPath);
		if (!rootDir.exists()) {
			rootDir.mkdir();
		}
		if (!yearDir.exists()) {
			yearDir.mkdir();
		}
		if (!monthDir.exists()) {
			monthDir.mkdir();
		}
		if (!dayDir.exists()) {
			dayDir.mkdir();
		}
		String destPath = dayPath + File.separator + suffixPath;
		File destFile = new File(destPath);
		if (!destFile.exists()) {
			try {
				destFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return destFile;
	}
}
