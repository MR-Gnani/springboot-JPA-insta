package com.cos.photogramstart.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.Service.UserService;
import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("dto", dto);
		return "user/profile";
	}
	
	// AuthenticationPrincipal "Authentication에 바로 접근해준다" 
	// --> Session - SecurityContextHolder - Authentication - PrincipalDetails
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		// 복잡한 방법
		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
		// System.out.println(mPrincipalDetails.getUser());
		
		// Header에 시큐리티 태그 라이브러리를 사용해서 전달하는 방법으로 대체
		// model.addAttribute("principal", principalDetails.getUser());
		return "user/update";
	}
}
