package com.talent518.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {
	@RequestMapping("/thymeleaf")
	public String show(Model model) {
		model.addAttribute("name", "阿宝");
		return "thymeleaf";
	}
}
