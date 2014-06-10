package com.oakhole.packet.cmpp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.oakhole.packet.SimplePacket;

public abstract class CMPPPacket extends SimplePacket {

	protected int Total_Length;
	protected int Command_Id;
	protected int Sequence_Id;

	public CMPPPacket() {
	}

	public CMPPPacket(CMPPPacket packet) {
		super(packet);
		this.Total_Length = packet.Total_Length;
		this.Command_Id = packet.Command_Id;
		this.Sequence_Id = packet.Sequence_Id;
	}

	public int getTotal_Length() {
		return Total_Length;
	}

	public void setTotal_Length(int total_Length) {
		Total_Length = total_Length;
	}

	public int getCommand_Id() {
		return Command_Id;
	}

	public void setCommand_Id(int command_Id) {
		Command_Id = command_Id;
	}

	public int getSequence_Id() {
		return Sequence_Id;
	}

	public void setSequence_Id(int sequence_Id) {
		Sequence_Id = sequence_Id;
	}

	@Override
	public void pack() throws IOException {
		this.dos.writeInt(Command_Id);
		this.dos.writeInt(Sequence_Id);
	}

	@Override
	public void unpack() throws IOException {
		this.Command_Id = this.dis.readInt();
		this.Sequence_Id = this.dis.readInt();
	}

	@Override
	public void writePacket(DataOutputStream dos) throws IOException {

		byte[] data = this.out.toByteArray();

		this.Total_Length = data.length + 4;

		ByteBuffer byteBuffer = ByteBuffer.allocate(this.Total_Length);
		byteBuffer.putInt(this.Total_Length);
		byteBuffer.put(data);

		dos.write(byteBuffer.array());
		dos.flush();
	}

	@Override
	public void readPacket(DataInputStream dis) throws IOException {

		this.Total_Length = dis.readInt();
		byte[] buffer = new byte[this.Total_Length - 4];
		dis.readFully(buffer);
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		DataInputStream input = new DataInputStream(in);
		this.setIn(in);
		this.setDis(input);
	}
}
