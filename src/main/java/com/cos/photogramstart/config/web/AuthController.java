package com.cos.photogramstart.config.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 1. IOC,  2. 파일을 리턴
public class AuthController {
	
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
	public String signup() {
		return "auth/signin"; // 회원가입이 진행되면 로그인창으로 이동
	}
	
}
