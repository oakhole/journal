package com.oakhole.ismg;

import com.oakhole.api.CMPP3Api;
import com.oakhole.packet.cmpp.*;
import com.oakhole.utils.MessageId;
import com.oakhole.utils.ReadConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SPCompany implements Runnable {

	private static Log logger = LogFactory.getLog(SPCompany.class);

	public static final int SLIDEWINDOW = 16;

	private String SP_Id;
	private String shared_secret;
	private String SP_Code;
	private String Service_Id;

	private String ip;
	private int flowLimit;
	private MessageId msg_id;

	private volatile boolean isRunning;

	private final long C = Long.valueOf(ReadConfig.getValue("C")), T = Long
			.valueOf(ReadConfig.getValue("T")), N = Long.valueOf(ReadConfig
			.getValue("N"));

	private volatile boolean alive = false;

	private CMPP3Api cmpp3Api;

	public SPCompany(Socket socket) {
		this.cmpp3Api = new CMPP3Api();
		this.msg_id = new MessageId();
		this.cmpp3Api.getAdapter().setSocket(socket);
		this.cmpp3Api.getAdapter().setTimeout(5 * 1000L);
		this.cmpp3Api.getConnection().open(this.cmpp3Api.getAdapter());
	}

    /**
     * 服务端与客户端对接
     * @return
     * @throws IOException
     */
	private boolean connect() throws IOException {

		if (!"192.168.1.105".equals(this.cmpp3Api.getAdapter().getSocket()
				.getInetAddress().getHostAddress())) {
			return false;
		}
		int status = 0;

		CMPPPacket packet = this.cmpp3Api.readPacket();

		if (packet == null) {
			status = 1;
		}

		if (packet instanceof CMPP_CONNECT) {
			CMPP_CONNECT connect = (CMPP_CONNECT) packet;

			String SP_Id = connect.getSource_Addr();

			if (!"999999".equals(SP_Id)) {
				status = 2;
			}

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("SP_Id", SP_Id);
			parameters.put("shared_secret", "9999");
			parameters.put("SP_Code", "1");
			parameters.put("Service_Id", "1");
			this.cmpp3Api.getAuthenticate().setParameters(parameters);
			this.msg_id.setSP_Code("1");

			byte[] authenticatorSP = this.cmpp3Api.getAuthenticate()
					.getAuthenticatorSP(connect.getTimestamp());

			if (authenticatorSP.length == connect.getAuthenticatorSource().length) {

				for (int i = 0; i < authenticatorSP.length; i++) {
					if (authenticatorSP[i] != connect.getAuthenticatorSource()[i]) {
						status = 3;
						break;
					}
					if (i >= authenticatorSP.length - 1) {
						this.cmpp3Api.setAuthenticated(true);
					}
				}
			} else {
				status = 3;
			}

			if (0x30 != connect.getVersion()) {
				status = 4;
			}

			CMPP_CONNECT_RESP connect_resp = new CMPP_CONNECT_RESP();

			byte[] authenticatorISMG = this.cmpp3Api.getAuthenticate()
					.getAuthenticatorISMG(0, connect.getAuthenticatorSource());

			connect_resp.setCommand_Id(CMPPCommandID.CONNECT_RESPONSE);
			connect_resp.setSequence_Id(connect.getSequence_Id());

			connect_resp.setStatus(status);
			connect_resp.setAuthenticatorISMG(authenticatorISMG);
			connect_resp.setVersion((byte) 0x30);

			this.cmpp3Api.writePacket(connect_resp);

			if (status == 0) {
				logger.info("SP_Id " + SP_Id + " login success");
			} else {
				logger.error("SP_Id " + SP_Id + " login failed," + status + ":"
						+ CMPPStatus.toString(status));
			}
		}
		return this.cmpp3Api.isAuthenticated();
	}

	private synchronized void read() throws IOException {

		if (this.cmpp3Api != null && this.cmpp3Api.available()) {
			CMPPPacket packet = this.cmpp3Api.readPacket();

			if (packet == null) {
				return;
			}

			if (packet instanceof CMPP_ACTIVE_TEST) {
				CMPP_ACTIVE_TEST active_test = (CMPP_ACTIVE_TEST) packet;
				CMPP_ACTIVE_TEST_RESP active_test_resp = new CMPP_ACTIVE_TEST_RESP();
				active_test_resp
						.setCommand_Id(CMPPCommandID.ACTIVETEST_RESPONSE);
				active_test_resp.setSequence_Id(active_test.getSequence_Id());
				active_test_resp.setReserved((byte) 0);
				this.cmpp3Api.writePacket(active_test_resp);
			}

			if (packet instanceof CMPP_ACTIVE_TEST_RESP) {
				this.alive = true;
			}

			if (packet instanceof CMPP_TERMINATE) {
				CMPP_TERMINATE terminate = (CMPP_TERMINATE) packet;
				CMPP_TERMINATE_RESP terminate_resp = new CMPP_TERMINATE_RESP();
				terminate_resp.setCommand_Id(CMPPCommandID.TERMINATE_RESPONSE);
				terminate_resp.setSequence_Id(terminate.getSequence_Id());
				this.cmpp3Api.writePacket(terminate_resp);
				this.close();
			}
			if (packet instanceof CMPP_TERMINATE_RESP) {
				this.close();
			}

			if (packet instanceof CMPP_DELIVER_RESP) {

			}

			if (packet instanceof CMPP_SUBMIT) {
				CMPP_SUBMIT submit = (CMPP_SUBMIT) packet;
				long msg_id = this.msg_id.generate();
				submit.setMsg_Id(msg_id);
				ISMGServer.submitQueue.add(submit);

				CMPP_SUBMIT_RESP submit_resp = new CMPP_SUBMIT_RESP();
				submit_resp.setCommand_Id(CMPPCommandID.SUBMIT_RESPONSE);
				submit_resp.setSequence_Id(submit.getSequence_Id());

				submit_resp.setMsg_Id(msg_id);
				submit_resp.setResult(0);
				this.cmpp3Api.writePacket(submit_resp);
			}
		}
	}

	private synchronized void write() {
		if (this.cmpp3Api != null) {

		}
	}

	private synchronized void active_test() {
		try {
			Thread.sleep(C);
			for (int i = 0; i < N; i++) {
				if (this.alive == true) {
					alive = false;
					return;
				}
				CMPP_ACTIVE_TEST cmpp_active_test = new CMPP_ACTIVE_TEST();
				cmpp_active_test.setCommand_Id(CMPPCommandID.ACTIVETEST);
				cmpp_active_test.setSequence_Id(this.cmpp3Api.nextSequence());
				this.cmpp3Api.writePacket(cmpp_active_test);
				Thread.sleep(T);
			}
			this.close();
		} catch (Exception e) {
			logger.info(e.getMessage());
			this.close();
		}
	}

	public void close() {
		this.isRunning = false;
		this.cmpp3Api.getAdapter().setInputStream(null);
		this.cmpp3Api.getAdapter().setOutputStream(null);
		try {
			this.cmpp3Api.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.cmpp3Api = null;
		}
	}

	@Override
	public void run() {
		try {
			this.isRunning = true;
			if (!this.connect()) {

				this.close();

			} else {
				Thread active_test = new Thread(new Runnable() {
					@Override
					public void run() {
						logger.info("process_active_test thread start");
						synchronized (this) {
							while (isRunning) {
								active_test();
							}
						}
					}
				});
				Thread reader = new Thread(new Runnable() {
					@Override
					public void run() {
						logger.info("process_reader thread start");
						synchronized (this) {
							while (isRunning) {
								try {
									read();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				});
				Thread writer = new Thread(new Runnable() {
					@Override
					public void run() {
						logger.info("process_writer thread start");
						synchronized (this) {
							while (isRunning) {
								write();
							}
						}
					}
				});
				reader.start();
				writer.start();
				active_test.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.cmpp3Api = null;
		this.msg_id = null;
	}

	public MessageId getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(MessageId msg_id) {
		this.msg_id = msg_id;
	}

	public String getSP_Id() {
		return SP_Id;
	}

	public void setSP_Id(String sP_Id) {
		SP_Id = sP_Id;
	}

	public String getShared_secret() {
		return shared_secret;
	}

	public void setShared_secret(String shared_secret) {
		this.shared_secret = shared_secret;
	}

	public String getSP_Code() {
		return SP_Code;
	}

	public void setSP_Code(String sP_Code) {
		SP_Code = sP_Code;
	}

	public String getService_Id() {
		return Service_Id;
	}

	public void setService_Id(String service_Id) {
		Service_Id = service_Id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getFlowLimit() {
		return flowLimit;
	}

	public void setFlowLimit(int flowLimit) {
		this.flowLimit = flowLimit;
	}

	public CMPP3Api getCmpp3Api() {
		return cmpp3Api;
	}

	public void setCmpp3Api(CMPP3Api cmpp3Api) {
		this.cmpp3Api = cmpp3Api;
	}

}
