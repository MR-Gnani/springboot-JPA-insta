package com.cos.photogramstart.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;

@Controller
public class UserController {

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "/user/profile";
	}
	
	// AuthenticationPrincipal "Authentication에 바로 접근해준다" 
	// --> Session - SecurityContextHolder - Authentication - PrincipalDetails
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		// 복잡한 방법
		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
		// System.out.println(mPrincipalDetails.getUser());
		
		return "/user/update";
	}
}
