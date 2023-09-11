package com.example.firstproject.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstApiController {
	
	@GetMapping("/api/hello")
	public String hello() {
		return "hello world!";
	}
	@GetMapping("/api/hi")
	public String niceToMeetYou(Model model) {
		return "greetings";
	}
}
