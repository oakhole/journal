package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_CANCEL_RESP extends CMPPPacket {
	public CMPP_CANCEL_RESP() {
	}

	public CMPP_CANCEL_RESP(CMPPPacket packet) {
		super(packet);
	}

	private int Success_Id;

	public int getSuccess_Id() {
		return Success_Id;
	}

	public void setSuccess_Id(int success_Id) {
		Success_Id = success_Id;
	}

	@Override
	public void pack() throws IOException {
		super.pack();
		this.dos.write(this.Success_Id);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Success_Id = this.dis.readInt();
	}

}
