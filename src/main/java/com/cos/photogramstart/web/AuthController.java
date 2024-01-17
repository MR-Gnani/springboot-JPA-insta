package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.Service.AuthService;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI할때 사용
@Controller // 1. IOC,  2. 파일을 리턴
public class AuthController {
	
	
	// private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private final AuthService authService;
// public AuthController(AuthService authService) {
// 	this.authService = authService;
//		}
	
	
	// 1. 로그인
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	// 2. 회원가입 
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	// 3. 회원가입 진행
	@PostMapping("/auth/signup")
	// 오류가 발생하면 "bindingResult"의 "getFieldErrors" 컬렉션에 다 모아준다. 
	// Controller에서 ResponseBody 어노테이션을 사용하면 데이터 리턴이 가능하다. 
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key=value(x-www-form-urlencoded)
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패", errorMap);
		} else {
			// User <-- SignupDto
			User user = signupDto.toEntity();
			// log.info(user.toString());
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			return "auth/signin"; // 회원가입이 진행되면 로그인창으로 이동
		}
	}
}
