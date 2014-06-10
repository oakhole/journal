package com.oakhole.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SimpleBuffer {
	public void writeString(DataOutputStream dos, String str, int len) {
		byte[] data = null;
		int srcLen = 0;
		try {
			if (str != null) {
				data = str.getBytes();
				srcLen = data.length;
				if (srcLen > len) {
					// throw new IOException("写入流中的数据超出长度");
					srcLen = len;
				}
				dos.write(data, 0, srcLen);
			}
			while (srcLen < len) {
				dos.write('\0');
				srcLen++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readString(DataInputStream dis, int len) {

		try {
			byte[] b = new byte[len];
			dis.read(b);
			String str = new String(b).trim();
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
