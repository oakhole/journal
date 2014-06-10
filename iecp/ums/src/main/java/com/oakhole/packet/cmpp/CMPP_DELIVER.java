package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_DELIVER extends CMPPPacket {

	public CMPP_DELIVER() {
	}

	public CMPP_DELIVER(CMPPPacket packet) {
		super(packet);
	}

	private long Msg_Id;
	private String Dest_Id;
	private String Service_Id;
	private byte TP_pid;
	private byte TP_udhi;
	private byte Msg_Fmt;
	private String Src_terminal_Id;
	private byte Src_terminal_type;
	private byte Registered_Delivery;
	private byte Msg_Length;
	private byte[] Msg_Content;
	private String LinkID;

	// instead of msg_content
	private long submit_msg_id;
	private String Stat;
	private String Submit_time;
	private String Done_time;
	private String Dest_terminal_Id;
	private int SMSC_sequence;

	public long getMsg_Id() {
		return Msg_Id;
	}

	public void setMsg_Id(long msg_Id) {
		Msg_Id = msg_Id;
	}

	public String getDest_Id() {
		return Dest_Id;
	}

	public void setDest_Id(String dest_Id) {
		Dest_Id = dest_Id;
	}

	public String getService_Id() {
		return Service_Id;
	}

	public void setService_Id(String service_Id) {
		Service_Id = service_Id;
	}

	public byte getTP_pid() {
		return TP_pid;
	}

	public void setTP_pid(byte tP_pid) {
		TP_pid = tP_pid;
	}

	public byte getTP_udhi() {
		return TP_udhi;
	}

	public void setTP_udhi(byte tP_udhi) {
		TP_udhi = tP_udhi;
	}

	public byte getMsg_Fmt() {
		return Msg_Fmt;
	}

	public void setMsg_Fmt(byte msg_Fmt) {
		Msg_Fmt = msg_Fmt;
	}

	public String getSrc_terminal_Id() {
		return Src_terminal_Id;
	}

	public void setSrc_terminal_Id(String src_terminal_Id) {
		Src_terminal_Id = src_terminal_Id;
	}

	public byte getSrc_terminal_type() {
		return Src_terminal_type;
	}

	public void setSrc_terminal_type(byte src_terminal_type) {
		Src_terminal_type = src_terminal_type;
	}

	public byte getRegistered_Delivery() {
		return Registered_Delivery;
	}

	public void setRegistered_Delivery(byte registered_Delivery) {
		Registered_Delivery = registered_Delivery;
	}

	public byte getMsg_Length() {
		return Msg_Length;
	}

	public void setMsg_Length(byte msg_Length) {
		Msg_Length = msg_Length;
	}

	public byte[] getMsg_Content() {
		return Msg_Content;
	}

	public void setMsg_Content(byte[] msg_Content) {
		Msg_Content = msg_Content;
	}

	public String getLinkID() {
		return LinkID;
	}

	public void setLinkID(String linkID) {
		LinkID = linkID;
	}

	public long getSubmit_msg_id() {
		return submit_msg_id;
	}

	public void setSubmit_msg_id(long submit_msg_id) {
		this.submit_msg_id = submit_msg_id;
	}

	public String getStat() {
		return Stat;
	}

	public void setStat(String stat) {
		Stat = stat;
	}

	public String getSubmit_time() {
		return Submit_time;
	}

	public void setSubmit_time(String submit_time) {
		Submit_time = submit_time;
	}

	public String getDone_time() {
		return Done_time;
	}

	public void setDone_time(String done_time) {
		Done_time = done_time;
	}

	public String getDest_terminal_Id() {
		return Dest_terminal_Id;
	}

	public void setDest_terminal_Id(String dest_terminal_Id) {
		Dest_terminal_Id = dest_terminal_Id;
	}

	public int getSMSC_sequence() {
		return SMSC_sequence;
	}

	public void setSMSC_sequence(int sMSC_sequence) {
		SMSC_sequence = sMSC_sequence;
	}

	@Override
	public void pack() throws IOException {
		super.pack();
		this.dos.writeLong(this.Msg_Id);
		this.writeString(dos, this.Dest_Id, 21);
		this.writeString(dos, this.Service_Id, 10);
		this.dos.writeByte(this.TP_pid);
		this.dos.writeByte(this.TP_udhi);
		this.dos.writeByte(this.Msg_Fmt);
		this.writeString(dos, this.Src_terminal_Id, 32);
		this.dos.writeByte(this.Src_terminal_type);
		this.dos.writeByte(this.Registered_Delivery);
		this.dos.writeByte(this.Msg_Length);
		if (this.Registered_Delivery == 0) {
			this.dos.write(this.Msg_Content);
		} else {
			this.dos.writeLong(this.submit_msg_id);
			this.writeString(dos, this.Stat, 7);
			this.writeString(dos, this.Submit_time, 10);
			this.writeString(dos, this.Done_time, 10);
			this.writeString(dos, this.Dest_terminal_Id, 32);
			this.dos.writeInt(this.SMSC_sequence);
		}
		this.writeString(dos, this.LinkID, 20);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Msg_Id = this.dis.readLong();
		this.Dest_Id = this.readString(dis, 21);
		this.Service_Id = this.readString(dis, 10);
		this.TP_pid = this.dis.readByte();
		this.TP_udhi = this.dis.readByte();
		this.Msg_Fmt = this.dis.readByte();
		this.Src_terminal_Id = this.readString(dis, 32);
		this.Src_terminal_type = this.dis.readByte();
		this.Registered_Delivery = this.dis.readByte();
		this.Msg_Length = this.dis.readByte();
		if (this.Registered_Delivery == 0) {
			this.Msg_Content = this.readString(dis, this.Msg_Length).getBytes();
		} else {
			this.submit_msg_id = this.dis.readLong();
			this.Stat = this.readString(dis, 7);
			this.Submit_time = this.readString(dis, 10);
			this.Done_time = this.readString(dis, 10);
			this.Dest_terminal_Id = this.readString(dis, 32);
			this.SMSC_sequence = this.dis.readInt();
		}
		this.LinkID = this.readString(dis, 20);
	}

}
