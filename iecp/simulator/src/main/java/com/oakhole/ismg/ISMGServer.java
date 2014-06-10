package com.oakhole.ismg;

import com.oakhole.db.DBHelper;
import com.oakhole.packet.cmpp.CMPP_DELIVER;
import com.oakhole.packet.cmpp.CMPP_SUBMIT;
import com.oakhole.utils.ReadConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ISMGServer implements Runnable {

	private static Log logger = LogFactory.getLog(ISMGServer.class);

	public static ConcurrentLinkedQueue<CMPP_SUBMIT> submitQueue;
	public static ConcurrentLinkedQueue<CMPP_DELIVER> deliverQueue;

	private InetSocketAddress inetSocketAddress = null;
	private ServerSocket serverSocket = null;

	public ISMGServer(String host, int port, int backlog) {

		submitQueue = new ConcurrentLinkedQueue<CMPP_SUBMIT>();
		deliverQueue = new ConcurrentLinkedQueue<CMPP_DELIVER>();

		this.inetSocketAddress = new InetSocketAddress(host, port);
		try {
			this.serverSocket = new ServerSocket(port, backlog,
					this.inetSocketAddress.getAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized void processSubmit() {

		if (ISMGServer.submitQueue.size() > 0) {

			List<CMPP_SUBMIT> submitList = new ArrayList<CMPP_SUBMIT>();
			submitList.addAll(ISMGServer.submitQueue);
			ISMGServer.submitQueue.clear();
			DBHelper.insertSubmitList(submitList);
		}
	}

	@Override
	public void run() {
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				logger.info("CMPP ISMG Server Start on "
						+ serverSocket.getInetAddress().getHostAddress() + ":"
						+ serverSocket.getLocalPort());
				submitQueue = new ConcurrentLinkedQueue<CMPP_SUBMIT>();
				deliverQueue = new ConcurrentLinkedQueue<CMPP_DELIVER>();
			}

			Thread processSubmit = new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("process_submit thread start");
					while (true) {
						processSubmit();
					}
				}
			});

			Thread processDeliver = new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("process_deliver thread start");
					while (true) {

					}
				}
			});

			processSubmit.start();
			processDeliver.start();

			while (true) {
				Socket sp = serverSocket.accept();
				if (sp != null && !sp.isClosed()) {
					String ip = sp.getInetAddress().getHostAddress();
					logger.info("SP accept on " + ip + ":" + sp.getPort());
					SPCompany spCompany = new SPCompany(sp);
					DBHelper.existsIp(ip);
					new Thread(spCompany).start();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String host = ReadConfig.getValue("host");
		int port = Integer.valueOf(ReadConfig.getValue("port"));
		int backlog = Integer.valueOf(ReadConfig.getValue("backlog"));

		ISMGServer cmpp_server = new ISMGServer(host, port, backlog);
		Thread cmpp = new Thread(cmpp_server);
		cmpp.start();
	}
}
