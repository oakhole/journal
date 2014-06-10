package com.oakhole.adapter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SocketAdapter extends SimpleAdapter {

	private static Log logger = LogFactory.getLog(SocketAdapter.class);

	private Socket socket;

	private InputStream inputStream;
	private OutputStream outputStream;

	public SocketAdapter() {
	}

	public SocketAdapter(String remoteAdress, int port) {
		try {
			socket = new Socket(remoteAdress, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SocketAdapter(Socket socket) {
		this.socket = socket;
	}

	public String getRemoteHost() {
		InetAddress address = socket.getInetAddress();
		return address.getHostAddress();
	}

	public int getPort() {
		return this.socket == null ? -1 : this.socket.getPort();
	}

	@Override
	public void reset() {
		this.inputStream = null;
		this.outputStream = null;
		this.socket = null;
	}

	@Override
	public void open() {
		try {
			this.inputStream = this.socket.getInputStream();
			this.outputStream = this.socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {

		try {
			if (this.inputStream != null) {
				this.inputStream.close();
			}
			if (this.outputStream != null) {
				this.outputStream.close();
			}
			if (this.socket != null) {
				this.socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isClosed() {
		return this.socket.isClosed();
	}

	@Override
	public boolean isTerminate() {
		return this.inputStream == null || this.outputStream == null;
	}

	public void setTimeout(long millsecond) {
		try {
			this.socket.setSoTimeout((int) millsecond);
		} catch (SocketException e) {
			logger.error("Unknown host or port not opened.");
			e.printStackTrace();
		}
	}

	@Override
	public boolean available() {
		try {
			return this.inputStream.available() > 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public DataOutputStream getDataOutputStream() {

		return new DataOutputStream(this.outputStream);
	}

	@Override
	public DataInputStream getDataInputStream() {
		return new DataInputStream(this.inputStream);
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

}
