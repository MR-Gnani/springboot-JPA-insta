package com.cos.photogramstart.handler.ex;

public class CustomApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	//생성자
	public CustomApiException(String message) {
		super(message);
	}

}
