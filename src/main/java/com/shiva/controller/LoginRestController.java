package com.shiva.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {
	@GetMapping
	public String one() {
		return null;
	}
}
