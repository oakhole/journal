package com.oakhole.packet.cmpp;

public class CMPPResult {

	public static String toString(int result) {
		switch (result) {
		case 0:
			return "正确";
		case 1:
			return "消息结构错";
		case 2:
			return "命令字错";
		case 3:
			return "消息序号重复";
		case 4:
			return "消息长度错";
		case 5:
			return "资费代码错";
		case 6:
			return "超过最大信息长";
		case 7:
			return "业务代码错";
		case 8:
			return "流量控制错";
		case 9:
			return "本网关不负责服务此计费号码";
		case 10:
			return "Src_Id错误";
		case 11:
			return "Msg_src错误";
		case 12:
			return "Fee_terminal_Id错误";
		case 13:
			return "Dest_terminal_Id错误";
		default:
			return "未知错误";
		}
	}
}
