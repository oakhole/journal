package com.oakhole.sp;

import com.oakhole.api.CMPP3Api;
import com.oakhole.packet.cmpp.*;

import java.io.IOException;

public class SPServer extends Thread {

	private int sendCount = 0;
	private int reportCount = 0;

	private String SP_Id = "999999";
	private String shared_secret = "9999";
	private String SP_Code = "160021";
	private String Service_Id = "9999";
	private CMPP3Api cmpp3Api = null;

	private volatile boolean isRunning = false;

	private int slidewindow = 0;

	public SPServer() {
		cmpp3Api = new CMPP3Api(SP_Id, shared_secret, SP_Code, Service_Id);
	}

	private synchronized void submit() throws IOException {

		CMPP_SUBMIT submit = new CMPP_SUBMIT();

		submit.setCommand_Id(CMPPCommandID.SUBMIT);
		submit.setSequence_Id(cmpp3Api.nextSequence());

		submit.setMsg_Id(1L);
		submit.setPk_total((byte) 1);
		submit.setPk_number((byte) 1);
		submit.setRegistered_Delivery((byte) 1);
		submit.setMsg_level((byte) 1);
		submit.setService_Id(Service_Id);
		submit.setFee_UserType((byte) 0);
		submit.setFee_terminal_Id(null);
		submit.setFee_terminal_type((byte) 1);
		submit.setTP_pId((byte) 0);
		submit.setTP_udhi((byte) 0);
		submit.setMsg_Fmt((byte) 0);
		submit.setMsg_src(SP_Id);
		submit.setFeeType("01");
		submit.setFeeCode("000000");
		submit.setValId_Time("");
		submit.setAt_Time("");
		submit.setSrc_Id(SP_Code);
		submit.setDestUsr_tl((byte) 1);
		submit.setDest_terminal_Id(new String[] { "13800000000" });
		submit.setDest_terminal_type((byte) 0);
		submit.setMsg_Content("Hello World".getBytes());
		submit.setMsg_Length((byte) submit.getMsg_Content().length);
		submit.setLinkID("012345678901234567890");

		cmpp3Api.writePacket(submit);
	}

	private synchronized void deliver() throws IOException {
		if (cmpp3Api.available()) {
			CMPPPacket packet = cmpp3Api.readPacket();
			if (packet != null) {

				synchronized (this) {
					if (slidewindow > 0) {
						slidewindow--;
						reportCount++;
						print();
					}
				}

				if (packet instanceof CMPP_SUBMIT_RESP) {
					// CMPP_SUBMIT_RESP submit_resp = (CMPP_SUBMIT_RESP) packet;
				}
				if (packet instanceof CMPP_ACTIVE_TEST) {
					CMPP_ACTIVE_TEST active_test = (CMPP_ACTIVE_TEST) packet;
					CMPP_ACTIVE_TEST_RESP active_test_resp = new CMPP_ACTIVE_TEST_RESP();
					active_test_resp
							.setCommand_Id(CMPPCommandID.ACTIVETEST_RESPONSE);
					active_test_resp.setSequence_Id(active_test
							.getSequence_Id());
					cmpp3Api.writePacket(active_test_resp);
				}
			}
		}
	}

	public void print() {
		System.out.println("slidewindow --> " + slidewindow);
		System.out.println("sendCount-->" + sendCount);
		System.out.println("reportCount-->" + reportCount);
	}

	@Override
	public void run() {
		try {
			if (cmpp3Api.connect("192.168.1.105", 7890)) {

				this.isRunning = true;

				Thread submit = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							while (isRunning) {
								synchronized (this) {
									if (slidewindow < 16) {
										submit();
										slidewindow++;
										sendCount++;
										print();
									}
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				Thread deliver = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							while (isRunning) {
								deliver();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				submit.start();
				deliver.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SPServer sp = new SPServer();
		sp.start();
	}
}
