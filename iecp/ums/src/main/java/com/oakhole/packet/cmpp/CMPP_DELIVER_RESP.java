package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_DELIVER_RESP extends CMPPPacket {

	public CMPP_DELIVER_RESP() {
	}

	public CMPP_DELIVER_RESP(CMPPPacket packet) {
		super(packet);
	}

	private long Msg_Id;
	private int Result;

	public long getMsg_Id() {
		return Msg_Id;
	}

	public void setMsg_Id(long msg_Id) {
		Msg_Id = msg_Id;
	}

	public int getResult() {
		return Result;
	}

	public void setResult(int result) {
		Result = result;
	}

	@Override
	public void pack() throws IOException {
		super.pack();
		this.dos.writeLong(this.Msg_Id);
		this.dos.writeInt(this.Result);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Msg_Id = this.dis.readLong();
		this.Result = this.dis.readInt();
	}

}
