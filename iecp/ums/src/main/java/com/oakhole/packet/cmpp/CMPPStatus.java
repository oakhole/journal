package com.oakhole.packet.cmpp;

public class CMPPStatus {

	public static String toString(int status) {
		switch (status) {
		case 0:
			return "正确";
		case 1:
			return "消息结构错";
		case 2:
			return "非法源地址";
		case 3:
			return "认证错";
		case 4:
			return "版本太高";
		case 5:
			return "其他错误";
		default:
			return "其他错误";
		}
	}
}
