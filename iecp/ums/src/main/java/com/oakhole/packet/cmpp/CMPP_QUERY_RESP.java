package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_QUERY_RESP extends CMPPPacket {
	public CMPP_QUERY_RESP() {
	}

	public CMPP_QUERY_RESP(CMPPPacket packet) {
		super(packet);
	}

	private String Time;
	private byte Query_Type;
	private String Query_Code;
	private int MT_TLMsg;
	private int MT_Tlusr;
	private int MT_Scs;
	private int MT_WT;
	private int MT_FL;
	private int MO_Scs;
	private int MO_WT;
	private int MO_FL;

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

	public int getMT_TLMsg() {
		return MT_TLMsg;
	}

	public void setMT_TLMsg(int mT_TLMsg) {
		MT_TLMsg = mT_TLMsg;
	}

	public int getMT_Tlusr() {
		return MT_Tlusr;
	}

	public void setMT_Tlusr(int mT_Tlusr) {
		MT_Tlusr = mT_Tlusr;
	}

	public int getMT_Scs() {
		return MT_Scs;
	}

	public void setMT_Scs(int mT_Scs) {
		MT_Scs = mT_Scs;
	}

	public int getMT_WT() {
		return MT_WT;
	}

	public void setMT_WT(int mT_WT) {
		MT_WT = mT_WT;
	}

	public int getMT_FL() {
		return MT_FL;
	}

	public void setMT_FL(int mT_FL) {
		MT_FL = mT_FL;
	}

	public int getMO_Scs() {
		return MO_Scs;
	}

	public void setMO_Scs(int mO_Scs) {
		MO_Scs = mO_Scs;
	}

	public int getMO_WT() {
		return MO_WT;
	}

	public void setMO_WT(int mO_WT) {
		MO_WT = mO_WT;
	}

	public int getMO_FL() {
		return MO_FL;
	}

	public void setMO_FL(int mO_FL) {
		MO_FL = mO_FL;
	}

	@Override
	public void pack() throws IOException {
		super.pack();
		this.writeString(dos, this.Time, 8);
		this.dos.writeByte(this.Query_Type);
		this.writeString(dos, this.Query_Code, 10);
		this.dos.writeInt(this.MT_TLMsg);
		this.dos.writeInt(this.MT_Tlusr);
		this.dos.writeInt(this.MT_Scs);
		this.dos.writeInt(this.MT_WT);
		this.dos.writeInt(this.MT_FL);
		this.dos.writeInt(this.MO_Scs);
		this.dos.writeInt(this.MO_WT);
		this.dos.writeInt(this.MO_FL);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Time = this.readString(dis, 8);
		this.Query_Type = this.dis.readByte();
		this.Query_Code = this.readString(dis, 10);
		this.MT_TLMsg = this.dis.readInt();
		this.MT_Tlusr = this.dis.readInt();
		this.MT_Scs = this.dis.readInt();
		this.MT_WT = this.dis.readInt();
		this.MT_FL = this.dis.readInt();
		this.MO_Scs = this.dis.readInt();
		this.MO_WT = this.dis.readInt();
		this.MO_FL = this.dis.readInt();
	}
}
