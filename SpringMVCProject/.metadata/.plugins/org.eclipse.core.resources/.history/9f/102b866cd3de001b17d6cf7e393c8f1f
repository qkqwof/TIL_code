package com.encore.boot01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BootController {
	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("data", "Boot Test");
		return "result";
	}
	
	@GetMapping("main.do")
	public String main(Model model) {
		model.addAttribute("message", "Boot Test2");
		return "find_ok";
	}
}
