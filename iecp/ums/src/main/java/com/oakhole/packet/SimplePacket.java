package com.oakhole.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SimplePacket extends SimpleBuffer {

	private static final int DEFAULT_BUFFER_SIZE = 2048;

	protected DataOutputStream dos;
	protected ByteArrayOutputStream out;

	protected DataInputStream dis;
	protected ByteArrayInputStream in;

	public SimplePacket() {
		this.out = new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE);
		this.dos = new DataOutputStream(out);
	}

	public SimplePacket(int maxSize) {
		this.out = new ByteArrayOutputStream(maxSize);
		this.dos = new DataOutputStream(out);
	}

	public SimplePacket(SimplePacket packet) {
		try {
			this.in = packet.in;
			this.dis = packet.dis;
			this.dis.reset();
			this.out = new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE);
			this.dos = new DataOutputStream(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pack() throws IOException {
	}

	public void unpack() throws IOException {
	}

	public void writePacket(DataOutputStream dos) throws IOException {
	}

	public void readPacket(DataInputStream dis) throws IOException {
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.in = null;
		this.out = null;
		this.dos = null;
		this.dis = null;
	}

	public DataInputStream getDis() {
		return dis;
	}

	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	public ByteArrayOutputStream getOut() {
		return out;
	}

	public void setOut(ByteArrayOutputStream out) {
		this.out = out;
	}

	public ByteArrayInputStream getIn() {
		return in;
	}

	public void setIn(ByteArrayInputStream in) {
		this.in = in;
	}
}
