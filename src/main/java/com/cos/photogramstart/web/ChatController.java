package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

	@GetMapping("/user/chat")
	public String chat() {
		return "chat/chat";
	}
	
	@RequestMapping("user/chating")
	public ModelAndView chating() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat/chating");
		return mv;
	}
}
