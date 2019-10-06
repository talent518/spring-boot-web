package com.talent518.demo.websocket.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talent518.demo.websocket.pojo.SocketMessage;
import com.talent518.demo.websocket.pojo.SocketResponse;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	SimpMessagingTemplate SMT;
	
	@MessageMapping("/getServerTime")
	public void serverTime(SocketMessage message) {
		SMT.convertAndSend("/topic/" + message.getGroup(), new SocketResponse(message.getName(), message.getMessage() + " " + dateFormat.format(new Date())));
	}
	
	@RequestMapping("")
	public String index(Model model, HttpServletRequest request) {
		model.addAttribute("baseUrl", request.getContextPath());
		return "websocket";
	}
}
