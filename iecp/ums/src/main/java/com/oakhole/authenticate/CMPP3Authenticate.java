package com.oakhole.authenticate;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

public class CMPP3Authenticate extends SimpleAuthenticate {

	public CMPP3Authenticate() {
	}

	public CMPP3Authenticate(Map<String, Object> parameters) {
		super(parameters);
	}

	public byte[] getAuthenticatorSP(int timestamp) {

		String account = (String) this.getParameters().get("SP_Id");
		String password = (String) this.getParameters().get("shared_secret");

		return DigestUtils
				.md5((account + "\0\0\0\0\0\0\0\0\0" + password + timestamp)
						.getBytes());
	}

	public byte[] getAuthenticatorISMG(int status, byte[] AuthenticatorSource) {

		String password = (String) this.getParameters().get("shared_secret");

		return DigestUtils
				.md5((status + new String(AuthenticatorSource) + password)
						.getBytes());
	}

}
