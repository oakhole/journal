package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_QUERY extends CMPPPacket {
	public CMPP_QUERY() {
	}

	public CMPP_QUERY(CMPPPacket packet) {
		super(packet);
	}

	private String Time;
	private byte Query_Type;
	private String Query_Code;
	private String Reserve;

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public byte getQuery_Type() {
		return Query_Type;
	}

	public void setQuery_Type(byte query_Type) {
		Query_Type = query_Type;
	}

	public String getQuery_Code() {
		return Query_Code;
	}

	public void setQuery_Code(String query_Code) {
		Query_Code = query_Code;
	}

	public String getReserve() {
		return Reserve;
	}

	public void setReserve(String reserve) {
		Reserve = reserve;
	}

	@Override
	public void pack() throws IOException {
		super.pack();
		this.writeString(dos, this.Time, 8);
		this.dos.writeByte(this.Query_Type);
		this.writeString(dos, this.Query_Code, 10);
		this.writeString(dos, this.Reserve, 8);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Time = this.readString(dis, 8);
		this.Query_Type = this.dis.readByte();
		this.Query_Code = this.readString(dis, 10);
		this.Reserve = this.readString(dis, 8);
	}

}
