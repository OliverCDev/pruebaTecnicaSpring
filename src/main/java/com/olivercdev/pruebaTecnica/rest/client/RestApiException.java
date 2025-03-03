package com.olivercdev.pruebaTecnica.rest.client;

public class RestApiException extends Exception {

    public RestApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RestApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestApiException(String message) {
		super(message);
	}

	public RestApiException(Throwable cause) {
		super(cause);
	}
}
