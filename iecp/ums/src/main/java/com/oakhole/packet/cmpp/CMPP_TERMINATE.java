package com.oakhole.packet.cmpp;

import java.io.IOException;

public class CMPP_TERMINATE extends CMPPPacket {
	public CMPP_TERMINATE() {
	}

	public CMPP_TERMINATE(CMPPPacket packet) {
		super(packet);
	}

	@Override
	public void pack() throws IOException {
		super.pack();
	}

	@Override
	public void unpack() throws IOException {
		super.unpack();
	}
}
