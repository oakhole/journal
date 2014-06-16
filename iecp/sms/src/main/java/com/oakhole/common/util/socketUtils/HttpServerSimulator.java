package com.oakhole.common.util.socketUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oakhole.common.util.constants.SocketConstants;

public class HttpServerSimulator implements Runnable {

	private static Log _log = LogFactory.getLog(HttpServerSimulator.class);

	private ServerSocket serverSocket;
	private ExecutorService pool;
	public boolean _switch = SocketConstants.OFF;

	public HttpServerSimulator(int port, int poolSize) throws IOException {
		if (!is_switch()) {
			set_switch(SocketConstants.ON);
			serverSocket = new ServerSocket(port);
			pool = Executors.newFixedThreadPool(poolSize);
			_log.info("Socket Server Start On Port " + port);
		}
	}

	public void run() {
		try {
			for (;;) { // try to use inner class to implements 
				pool.execute(new Handler(serverSocket.accept()));
			}
		} catch (IOException ex) {
			pool.shutdown();
		}
	}

	public static void main(String[] args) throws IOException {
		new Thread(new HttpServerSimulator(80, 10)).start();
	}

	public boolean is_switch() {
		return _switch;
	}

	public void set_switch(boolean _switch) {
		this._switch = _switch;
	}
}

class Handler implements Runnable {
	private final Socket socket;

	Handler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			InputStream in = this.socket.getInputStream();
			Writer out = new OutputStreamWriter(this.socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String requestString = br.readLine();

			if (requestString != null) {

				String method = requestString.split(" ")[0];
				String data = requestString.split(" ")[1];
				String version = requestString.split(" ")[2];

				if ("GET".equals(method.trim())) {
					System.out.println(data.substring(2));
				}

				if (version.startsWith("HTTP")) {
					out.write("HTTP/1.1 200 OK\r\n");
					out.write("Date" + new Date() + "\r\n");
					out.write("Server: JHTTP/1.0\r\n");
					out.write("Content-type: text/html \r\n\r\n");
					out.write("success");
					out.flush();
				}
			}

			in.close();
			br.close();
			out.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
