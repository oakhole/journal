package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_CANCEL extends CMPPPacket {
	public CMPP_CANCEL() {
	}

	public CMPP_CANCEL(CMPPPacket packet) {
		super(packet);
	}

	private long Msg_Id;

	public long getMsg_Id() {
		return Msg_Id;
	}

	public void setMsg_Id(long msg_Id) {
		Msg_Id = msg_Id;
	}

	@Override
	public void pack() throws IOException {
		super.pack();
		this.dos.writeLong(this.Msg_Id);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Msg_Id = this.dis.readLong();
	}

}
