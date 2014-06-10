package com.oakhole.authenticate;

import java.util.Map;

public class SimpleAuthenticate {

	private Map<String, Object> parameters;

	public SimpleAuthenticate() {
	}

	public SimpleAuthenticate(Map<String, Object> parameters) {
		super();
		this.parameters = parameters;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public byte[] getAuthenticate(Map<String, Object> parameters) {
		return null;
	}
}
