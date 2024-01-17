package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;

@RestController // 데이터 리턴
@ControllerAdvice // 모든 예외처리를 다 낚아챔
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
			return Script.back(e.getErrorMap().toString());
		// return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
	}
}
