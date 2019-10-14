package com.tuyji.util;

import org.springframework.http.HttpHeaders;

public final class HeaderUtil {

	private HeaderUtil() {
	}

	public static HttpHeaders createErrorAlert(String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-sentryApp-error", message);
		return headers;
	}

}
