package com.oakhole.packet.cmpp;

public class CMPPStatus {

	public static String toString(int status) {
		switch (status) {
		case 0:
			return "��ȷ";
		case 1:
			return "��Ϣ�ṹ��";
		case 2:
			return "�Ƿ�Դ��ַ";
		case 3:
			return "��֤��";
		case 4:
			return "�汾̫��";
		case 5:
			return "��������";
		default:
			return "��������";
		}
	}
}
