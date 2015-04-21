package com.alvardev.demos.shopmedical.http;

public class HttpResult {

	private String data;
	private int statusCode;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public HttpResult(String data, int statusCode) {
		super();
		this.data = data;
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
