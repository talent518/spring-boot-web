package com.talent518.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent518.demo.rabbitmq.Producer;

@RestController
@RequestMapping("/rabbitMQ")
public class RabbitMQController {
	@Autowired
	private Producer producer;
	
	@RequestMapping("/toggleSchedule")
	public String toggleSchedule() {
		return producer.toggleSchedule() ? "Closed schedule." : "Started schedule.";
	}
	
	@RequestMapping("/send/{message}")
	public String send(@PathVariable String message) {
		producer.send("web", message);
		
		return "<script type=\"text/javascript\">alert('Sended to \\'web\\' queue');history.back();</script>";
	}
}
