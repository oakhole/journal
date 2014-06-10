package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_ACTIVE_TEST_RESP extends CMPPPacket {
	public CMPP_ACTIVE_TEST_RESP() {
	}

	public CMPP_ACTIVE_TEST_RESP(CMPPPacket packet) {
		super(packet);
	}

	private byte Reserved;

	public byte getReserved() {
		return Reserved;
	}

	public void setReserved(byte reserved) {
		Reserved = reserved;
	}

	@Override
	public void pack() throws IOException {
		this.dos.write(this.Reserved);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Reserved = this.dis.readByte();
	}

}
