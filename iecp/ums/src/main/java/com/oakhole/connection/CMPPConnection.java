package com.oakhole.connection;

import com.oakhole.adapter.SocketAdapter;
import com.oakhole.packet.SimplePacket;
import com.oakhole.packet.cmpp.CMPPCommandID;
import com.oakhole.packet.cmpp.CMPPPacket;
import com.oakhole.packet.cmpp.CMPP_ACTIVE_TEST;
import com.oakhole.packet.cmpp.CMPP_ACTIVE_TEST_RESP;
import com.oakhole.packet.cmpp.CMPP_CANCEL;
import com.oakhole.packet.cmpp.CMPP_CANCEL_RESP;
import com.oakhole.packet.cmpp.CMPP_CONNECT;
import com.oakhole.packet.cmpp.CMPP_CONNECT_RESP;
import com.oakhole.packet.cmpp.CMPP_DELIVER;
import com.oakhole.packet.cmpp.CMPP_DELIVER_RESP;
import com.oakhole.packet.cmpp.CMPP_QUERY;
import com.oakhole.packet.cmpp.CMPP_QUERY_RESP;
import com.oakhole.packet.cmpp.CMPP_SUBMIT;
import com.oakhole.packet.cmpp.CMPP_SUBMIT_RESP;
import com.oakhole.packet.cmpp.CMPP_TERMINATE;
import com.oakhole.packet.cmpp.CMPP_TERMINATE_RESP;

public class CMPPConnection extends SimpleConnection {

	public CMPPConnection() {
	}

	public CMPPConnection(SocketAdapter adapter) {
		super(adapter);
	}

	@Override
	public SimplePacket createPacket(SimplePacket packet) {

		CMPPPacket cmppPacket = null;

		if (packet instanceof CMPPPacket) {

			cmppPacket = (CMPPPacket) packet;

			switch (cmppPacket.getCommand_Id()) {
			case CMPPCommandID.ACTIVETEST:
				return new CMPP_ACTIVE_TEST(cmppPacket);
			case CMPPCommandID.ACTIVETEST_RESPONSE:
				return new CMPP_ACTIVE_TEST_RESP(cmppPacket);
			case CMPPCommandID.CANCEL:
				return new CMPP_CANCEL(cmppPacket);
			case CMPPCommandID.CANCEL_RESPONSE:
				return new CMPP_CANCEL_RESP(cmppPacket);
			case CMPPCommandID.CONNECT:
				return new CMPP_CONNECT(cmppPacket);
			case CMPPCommandID.CONNECT_RESPONSE:
				return new CMPP_CONNECT_RESP(cmppPacket);
			case CMPPCommandID.DELIVER:
				return new CMPP_DELIVER(cmppPacket);
			case CMPPCommandID.DELIVER_RESPONSE:
				return new CMPP_DELIVER_RESP(cmppPacket);
			case CMPPCommandID.QUERY:
				return new CMPP_QUERY(cmppPacket);
			case CMPPCommandID.QUERY_RESPONSE:
				return new CMPP_QUERY_RESP(cmppPacket);
			case CMPPCommandID.SUBMIT:
				return new CMPP_SUBMIT(cmppPacket);
			case CMPPCommandID.SUBMIT_RESPONSE:
				return new CMPP_SUBMIT_RESP(cmppPacket);
			case CMPPCommandID.TERMINATE:
				return new CMPP_TERMINATE(cmppPacket);
			case CMPPCommandID.TERMINATE_RESPONSE:
				return new CMPP_TERMINATE_RESP(cmppPacket);
			}
		}
		return null;
	}
}
