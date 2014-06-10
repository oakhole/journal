package com.oakhole.adapter;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class SimpleAdapter {

	public SimpleAdapter() {
	}

	public void open() {

	}

	public void close() {

	}

	public void reset() {

	}

	public boolean isClosed() {
		return false;
	}

	public boolean isTerminate() {
		return false;
	}

	public boolean available() {
		return false;
	}

	public DataOutputStream getDataOutputStream() {
		return null;
	}

	public DataInputStream getDataInputStream() {
		return null;
	}
}
