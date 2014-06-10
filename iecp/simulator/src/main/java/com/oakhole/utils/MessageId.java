package com.oakhole.utils;

import java.math.BigInteger;
import java.util.Calendar;

public class MessageId {

	private int msg_id;
	private String SP_Code;

	public MessageId() {
		this.msg_id = 0;
	}

	public MessageId(String SP_Code) {
		this.msg_id = 0;
		this.SP_Code = SP_Code;
	}

	private synchronized short nextShort() {
		if (msg_id >= 0x7fff) {
			msg_id = 1;
		} else {
			msg_id++;
		}
		return (short) msg_id;
	}

	private String toBinaryString(int i, int len) {
		String src = Integer.toBinaryString(i);
		String dest = "";
		int srcLen = src.length();
		while (len > srcLen) {
			srcLen++;
			dest += "0";
		}
		dest += src;
		return dest;
	}

	private long binaryStringToLong(String binaryString) {
		BigInteger src = new BigInteger(binaryString, 2);
		return src.longValue();
	}

	public synchronized long generate() {
		StringBuffer sb = new StringBuffer();
		Calendar cl = Calendar.getInstance();
		sb.append(toBinaryString(cl.get(Calendar.MONTH) + 1, 4));
		sb.append(toBinaryString(cl.get(Calendar.DATE), 5));
		sb.append(toBinaryString(cl.get(Calendar.HOUR_OF_DAY), 5));
		sb.append(toBinaryString(cl.get(Calendar.MINUTE), 6));
		sb.append(toBinaryString(cl.get(Calendar.SECOND), 6));
		sb.append(toBinaryString(Integer.valueOf(this.SP_Code), 22));
		sb.append(toBinaryString(nextShort(), 16));
		return binaryStringToLong(sb.toString());
	}

	public String getSP_Code() {
		return SP_Code;
	}

	public void setSP_Code(String sP_Code) {
		SP_Code = sP_Code;
	}

	public static void main(String[] args) {
		MessageId messageid = new MessageId("1");
		long msg_id = messageid.generate();
		System.out.println(msg_id + "\n" + Long.toBinaryString(msg_id));
	}
}
