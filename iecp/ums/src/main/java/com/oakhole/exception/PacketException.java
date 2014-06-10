package com.oakhole.exception;

@SuppressWarnings("serial")
public class PacketException extends Exception {

	public PacketException() {
	}

	public PacketException(String err) {
		super(err);
	}
}
