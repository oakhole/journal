package com.oakhole.packet.cmpp;

public class CMPPResult {

	public static String toString(int result) {
		switch (result) {
		case 0:
			return "��ȷ";
		case 1:
			return "��Ϣ�ṹ��";
		case 2:
			return "�����ִ�";
		case 3:
			return "��Ϣ����ظ�";
		case 4:
			return "��Ϣ���ȴ�";
		case 5:
			return "�ʷѴ����";
		case 6:
			return "���������Ϣ��";
		case 7:
			return "ҵ������";
		case 8:
			return "�������ƴ�";
		case 9:
			return "�����ز��������˼ƷѺ���";
		case 10:
			return "Src_Id����";
		case 11:
			return "Msg_src����";
		case 12:
			return "Fee_terminal_Id����";
		case 13:
			return "Dest_terminal_Id����";
		default:
			return "δ֪����";
		}
	}
}
