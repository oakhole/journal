package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_CONNECT_RESP extends CMPPPacket {

	public CMPP_CONNECT_RESP() {
	}

	public CMPP_CONNECT_RESP(CMPPPacket packet) {
		super(packet);
	}

	private int Status;
	private byte[] AuthenticatorISMG;
	private byte Version;

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public byte[] getAuthenticatorISMG() {
		return AuthenticatorISMG;
	}

	public void setAuthenticatorISMG(byte[] authenticatorISMG) {
		AuthenticatorISMG = authenticatorISMG;
	}

	public byte getVersion() {
		return Version;
	}

	public void setVersion(byte version) {
		Version = version;
	}

	@Override
	public void pack() throws IOException {
		super.pack();
		this.dos.writeInt(this.Status);
		this.dos.write(this.AuthenticatorISMG);
		this.dos.write(this.Version);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Status = this.dis.readInt();
		this.AuthenticatorISMG = this.readString(dis, 16).getBytes();
		this.Version = this.dis.readByte();

	}
}
