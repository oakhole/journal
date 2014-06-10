package com.oakhole.packet.cmpp;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class CMPP_SUBMIT extends CMPPPacket {
	public CMPP_SUBMIT() {
	}

	public CMPP_SUBMIT(CMPPPacket packet) {
		super(packet);
	}

	private long Msg_Id;
	private byte Pk_total;
	private byte Pk_number;
	private byte Registered_Delivery;
	private byte Msg_level;
	private String Service_Id;
	private byte Fee_UserType;
	private String Fee_terminal_Id;
	private byte Fee_terminal_type;
	private byte TP_pId;
	private byte TP_udhi;
	private byte Msg_Fmt;
	private String Msg_src;
	private String FeeType;
	private String FeeCode;
	private String ValId_Time;
	private String At_Time;
	private String Src_Id;
	private byte DestUsr_tl;
	private String[] Dest_terminal_Id;
	private byte Dest_terminal_type;
	private byte Msg_Length;
	private byte[] Msg_Content;
	private String LinkID;

	@Override
	public void pack() throws IOException {
		super.pack();
		this.dos.writeLong(this.Msg_Id);
		this.dos.write(this.Pk_total);
		this.dos.write(this.Pk_number);
		this.dos.write(this.Registered_Delivery);
		this.dos.write(this.Msg_level);
		this.writeString(this.dos, this.Service_Id, 10);
		this.dos.write(this.Fee_UserType);
		if (this.Fee_terminal_type == 0) {
			this.writeString(dos, this.Fee_terminal_Id, 32);
		} else {
			this.writeString(
					dos,
					this.Fee_terminal_Id == null ? null : new String(Base64
							.decodeBase64(this.Fee_terminal_Id.getBytes())), 32);
		}
		this.dos.write(this.Fee_terminal_type);
		this.dos.write(this.TP_pId);
		this.dos.write(this.TP_udhi);
		this.dos.write(this.Msg_Fmt);
		this.writeString(dos, this.Msg_src, 6);
		this.writeString(dos, this.FeeType, 2);
		this.writeString(dos, this.FeeCode, 6);
		this.writeString(dos, this.ValId_Time, 17);
		this.writeString(dos, this.At_Time, 17);
		this.writeString(dos, this.Src_Id, 21);
		this.dos.write(this.DestUsr_tl);
		for (int i = 0; i < this.DestUsr_tl; i++) {
			if (this.Dest_terminal_type == 0) {
				this.writeString(dos, this.Dest_terminal_Id[i], 32);
			} else {
				this.writeString(
						dos,
						new String(Base64.decodeBase64(this.Dest_terminal_Id[i]
								.getBytes())), 32);
			}
		}
		this.dos.write(this.Dest_terminal_type);
		this.dos.write(this.Msg_Length);
		this.dos.write(this.Msg_Content);
		this.writeString(dos, this.LinkID, 20);
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
		this.Msg_Id = this.dis.readLong();
		this.Pk_total = this.dis.readByte();
		this.Pk_number = this.dis.readByte();
		this.Registered_Delivery = this.dis.readByte();
		this.Msg_level = this.dis.readByte();
		this.Service_Id = this.readString(dis, 10);
		this.Fee_UserType = this.dis.readByte();
		this.Fee_terminal_Id = this.readString(dis, 32);
		this.Fee_terminal_type = this.dis.readByte();
		this.TP_pId = this.dis.readByte();
		this.TP_udhi = this.dis.readByte();
		this.Msg_Fmt = this.dis.readByte();
		this.Msg_src = this.readString(dis, 6);
		this.FeeType = this.readString(dis, 2);
		this.FeeCode = this.readString(dis, 6);
		this.ValId_Time = this.readString(dis, 17);
		this.At_Time = this.readString(dis, 17);
		this.Src_Id = this.readString(dis, 21);
		this.DestUsr_tl = this.dis.readByte();
		this.Dest_terminal_Id = new String[this.DestUsr_tl];
		for (int i = 0; i < this.DestUsr_tl; i++) {
			this.Dest_terminal_Id[i] = this.readString(dis, 32);
		}
		this.Dest_terminal_type = this.dis.readByte();
		if (this.Dest_terminal_type == 1) {
			for (int i = 0; i < this.DestUsr_tl; i++) {
				this.Dest_terminal_Id[i] = new String(
						Base64.decodeBase64(this.Dest_terminal_Id[i].getBytes()));
			}
		}
		this.Msg_Length = this.dis.readByte();
		this.Msg_Content = this.readString(dis, this.Msg_Length).getBytes();
		this.LinkID = this.readString(dis, 20);
	}

	public long getMsg_Id() {
		return Msg_Id;
	}

	public void setMsg_Id(long msg_Id) {
		Msg_Id = msg_Id;
	}

	public byte getPk_total() {
		return Pk_total;
	}

	public void setPk_total(byte pk_total) {
		Pk_total = pk_total;
	}

	public byte getPk_number() {
		return Pk_number;
	}

	public void setPk_number(byte pk_number) {
		Pk_number = pk_number;
	}

	public byte getRegistered_Delivery() {
		return Registered_Delivery;
	}

	public void setRegistered_Delivery(byte registered_Delivery) {
		Registered_Delivery = registered_Delivery;
	}

	public byte getMsg_level() {
		return Msg_level;
	}

	public void setMsg_level(byte msg_level) {
		Msg_level = msg_level;
	}

	public String getService_Id() {
		return Service_Id;
	}

	public void setService_Id(String service_Id) {
		Service_Id = service_Id;
	}

	public byte getFee_UserType() {
		return Fee_UserType;
	}

	public void setFee_UserType(byte fee_UserType) {
		Fee_UserType = fee_UserType;
	}

	public String getFee_terminal_Id() {
		return Fee_terminal_Id;
	}

	public void setFee_terminal_Id(String fee_terminal_Id) {
		Fee_terminal_Id = fee_terminal_Id;
	}

	public byte getFee_terminal_type() {
		return Fee_terminal_type;
	}

	public void setFee_terminal_type(byte fee_terminal_type) {
		Fee_terminal_type = fee_terminal_type;
	}

	public byte getTP_pId() {
		return TP_pId;
	}

	public void setTP_pId(byte tP_pId) {
		TP_pId = tP_pId;
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

	public String getMsg_src() {
		return Msg_src;
	}

	public void setMsg_src(String msg_src) {
		Msg_src = msg_src;
	}

	public String getFeeType() {
		return FeeType;
	}

	public void setFeeType(String feeType) {
		FeeType = feeType;
	}

	public String getFeeCode() {
		return FeeCode;
	}

	public void setFeeCode(String feeCode) {
		FeeCode = feeCode;
	}

	public String getValId_Time() {
		return ValId_Time;
	}

	public void setValId_Time(String valId_Time) {
		ValId_Time = valId_Time;
	}

	public String getAt_Time() {
		return At_Time;
	}

	public void setAt_Time(String at_Time) {
		At_Time = at_Time;
	}

	public String getSrc_Id() {
		return Src_Id;
	}

	public void setSrc_Id(String src_Id) {
		Src_Id = src_Id;
	}

	public byte getDestUsr_tl() {
		return DestUsr_tl;
	}

	public void setDestUsr_tl(byte destUsr_tl) {
		DestUsr_tl = destUsr_tl;
	}

	public String[] getDest_terminal_Id() {
		return Dest_terminal_Id;
	}

	public void setDest_terminal_Id(String[] dest_terminal_Id) {
		Dest_terminal_Id = dest_terminal_Id;
	}

	public byte getDest_terminal_type() {
		return Dest_terminal_type;
	}

	public void setDest_terminal_type(byte dest_terminal_type) {
		Dest_terminal_type = dest_terminal_type;
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

}
