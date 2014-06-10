package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_CONNECT extends CMPPPacket {

	private String Source_Addr;
	private byte[] AuthenticatorSource;
	private byte Version;
	private int Timestamp;

	public CMPP_CONNECT() {
	}

	public CMPP_CONNECT(CMPPPacket packet) {
		super(packet);
	}

	public String getSource_Addr() {
		return Source_Addr;
	}

	public void setSource_Addr(String source_Addr) {
		Source_Addr = source_Addr;
	}

	public byte[] getAuthenticatorSource() {
		return AuthenticatorSource;
	}

	public void setAuthenticatorSource(byte[] authenticatorSource) {
		AuthenticatorSource = authenticatorSource;
	}

	public byte getVersion() {
		return Version;
	}

	public void setVersion(byte version) {
		Version = version;
	}

	public int getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(int timestamp) {
		Timestamp = timestamp;
	}

	@Override
	public void pack() throws IOException {
		super.pack();
		this.writeString(dos, this.Source_Addr, 6);
		this.dos.write(this.AuthenticatorSource);
		this.dos.write(this.Version);
		this.dos.writeInt(this.Timestamp);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Source_Addr = this.readString(dis, 6);
		byte[] authenticatorSource = new byte[16];
		this.dis.read(authenticatorSource);
		this.AuthenticatorSource = authenticatorSource;
		this.Version = this.dis.readByte();
		this.Timestamp = this.dis.readInt();
	}
}
