package com.talent518.demo.controller;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talent518.demo.service.IAsyncService;

@RestController
public class AsyncController {
	@Autowired
	private IAsyncService asyncService;
	
	@RequestMapping("/async")
	public String async() throws Exception {
		long t = System.currentTimeMillis();
		
		Future<String> task1 = asyncService.task1();
		Future<String> task2 = asyncService.task2();
		Future<String> task3 = asyncService.task3();
		
		while(!(task1.isDone() && task2.isDone() && task3.isDone())) Thread.sleep(1);
		
		long t2 = System.currentTimeMillis();
		
		return "run time: " + (t2 - t) + " ms";
	}
}
