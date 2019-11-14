package com.talent518.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talent518.demo.entity.Result;
import com.talent518.demo.mail.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {
	@Autowired
	MailService mailService;

	@RequestMapping("/send")
	public Result send(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("content") String content) throws Exception {
		mailService.send(from, to, subject, content, new File[] { new File("pom.xml"), new File("src/main/resources/application.yml"), new File("src/main/resources/application-dev.yml") }, null);
		return Result.ok();
	}

	@RequestMapping("/template")
	public String template(@RequestParam("title") String title, @RequestParam("content") String content) throws Exception {
		Map<String, String> model = new HashMap<>();
		model.put("title", title);
		model.put("content", content);
		return mailService.template("mail.ftl", model);
	}
}
