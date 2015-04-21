package com.alvardev.demos.shopmedical.http;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public enum HttpMethod {
	GET(1), POST(2), PUT(3), DELETE(4), UNKNOWN(1000);
	private int value;
	@SuppressWarnings("rawtypes")
	private static final Map typesByValue = new HashMap();

	static {
		for (HttpMethod type : HttpMethod.values()) {
			typesByValue.put(type.value, type);
		}
	}

	private HttpMethod(int value) {
		this.value = value;

	}

	public int getValue() {
		return this.value;
	}

	public static HttpMethod forValue(int value) {
		if (typesByValue.containsKey(value)) {
			return (HttpMethod) typesByValue.get(value);
		} else {
			return (HttpMethod) typesByValue.get(1000);

		}
	}

}