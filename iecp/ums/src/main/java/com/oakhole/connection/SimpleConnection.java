package com.oakhole.connection;

import java.io.IOException;

import com.oakhole.adapter.SimpleAdapter;
import com.oakhole.packet.SimplePacket;

public abstract class SimpleConnection {

	protected SimpleAdapter adapter;

	public SimpleConnection() {
	}

	public SimpleConnection(SimpleAdapter adapter) {
		this.adapter = adapter;
	}

	public void open(SimpleAdapter adapter) {
		if (this.adapter == null) {
			this.adapter = adapter;
		}
		adapter.open();
	}

	public void close(SimpleAdapter adapter) {
		adapter.close();
	}

	public boolean isClosed() {
		return adapter.isClosed();
	}

	public boolean isTerminate() {
		return adapter.isTerminate();
	}

	public SimplePacket createPacket(SimplePacket packet) {
		return null;
	}

	public boolean available() {
		return this.adapter != null && this.adapter.available();
	}

	public SimplePacket readPacket(SimplePacket packet) throws IOException {
		packet.readPacket(this.adapter.getDataInputStream());
		packet.unpack();
		packet = createPacket(packet);
		packet.unpack();
		return packet;
	}

	public void writePacket(SimplePacket packet) throws IOException {
		packet.pack();
		packet.writePacket(this.adapter.getDataOutputStream());
	}
}
