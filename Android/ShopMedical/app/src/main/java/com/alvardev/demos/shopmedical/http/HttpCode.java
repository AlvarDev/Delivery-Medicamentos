package com.alvardev.demos.shopmedical.http;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public enum HttpCode {
	OK(200), BAD_REQUEST(400), NOT_FOUND(404), TIMEOUT(1000), ERROR(100), OTHER(2000);
	private int code;
	@SuppressWarnings("rawtypes")
	private static final Map typesByValue = new HashMap();

	static {
		for (HttpCode type : HttpCode.values()) {
			typesByValue.put(type.code, type);
		}
	}

	private HttpCode(int code) {
		this.code = code;

	}

	public int getCode() {
		return this.code;

	}

	public static HttpCode forValue(int value) {
		if (typesByValue.containsKey(value)) {
			return (HttpCode) typesByValue.get(value);
		} else {
			return (HttpCode) typesByValue.get(2000);

		}
	}

}
