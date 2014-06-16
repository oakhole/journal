package com.oakhole.common.util.constants;

import java.util.HashMap;
import java.util.Map;

public class GlobalConstants {

	/**
	 * 系统用户和角色
	 */
	public static final String GLOBALAPPUSER = "GLOBALAPPUSER";
	public static final String GLOBALAPPROLE = "GLOBALAPPROLE";

	/**
	 * 系统分页模板配置
	 */
	public static final int PAGESIZE = 12; // 每页显示长度
	public static final int PAGELENGTH = 7; // 数据表底部显示多少页码

	/**
	 * Quartz任务配置
	 * 
	 */
	public static final String TRIGGERNAME = "triggerName";
	public static final String TRIGGERGROUP = "triggerGroup";
	public static final String STARTTIME = "startTime";
	public static final String ENDTIME = "endTime";
	public static final String REPEATCOUNT = "repeatCount";
	public static final String REPEATINTERVEL = "repeatInterval";

	public static final Map<String, String> status = new HashMap<String, String>();
	static {
		status.put("ACQUIRED", "运行中");
		status.put("PAUSED", "暂停中");
		status.put("WAITING", "等待中");
	}

	public static final String SENDSMSGROUP = "SENDSMSGROUP";
	public static final String GETREPORTGROUP = "GETREPORTGROUP";
	public static final String GETMOSMSGROUP = "GETMOSMSGROUP";
	public static final String GETBALANCEGROUP = "GETBALANCEGROUP";
	public static final String WATCHDOGGROUP = "WATCHDOGGROUP";

	/**
	 * seg手机号段
	 */
	public static final String HEADOFYD = "134,135,136,137,138,139,147,150,151,152,157,158,159,182,183,184,187,188";
	public static final String HEADOFLT = "130,131,132,155,156,185,186";
	public static final String HEADOFDX = "180,181,189,133,153";

	/**
	 * 用于短信发送任务逐个批次数量统计
	 */
	public static int CURRENTPICI = 1;

	/**
	 * 设定用于获取http、webservice协议短信接口所需常量键值
	 */
	public static final String KEY_URL = "KEY_URL";
	public static final String KEY_MTURL = "KEY_MTURL";
	public static final String KEY_MOURL = "KEY_MOURL";
	public static final String KEY_STATUSURL = "KEY_STATUSURL";
	public static final String KEY_BALANCEURL = "KEY_BALANCEURL";

	/**
	 * 设定用于获取Socket协议短信接口所需常量键值
	 */
	public static final String KEY_SERVER_IP = "KEY_SERVER_IP";
	public static final String KEY_SERVER_PORT = "KEY_SERVER_PORT";
	public static final String KEY_SERVICE_ID = "KEY_SERVICE_ID";

	/**
	 * 根据发送号码数量确定优先级
	 * 
	 * @param sendPhoneCount
	 * @return
	 */
	public static int getPriority(int sendPhoneCount) {

		int condition = sendPhoneCount / 10;

		if (condition <= 0) {
			return 0;
		} else if (condition <= 10) {
			return 1;
		} else if (condition <= 100) {
			return 2;
		} else if (condition <= 1000) {
			return 3;
		} else if (condition <= 10000) {
			return 4;
		} else {
			return 5;
		}
	}

}
