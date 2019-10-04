package com.talent518.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/test")
	public Test dealTest(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Test(counter.incrementAndGet(), String.format(template, name));
	}

	public class Test {

		private final long id;
		private final String content;

		public Test(long id, String content) {
			this.id = id;
			this.content = content;
		}

		public long getId() {
			return id;
		}

		public String getContent() {
			return content;
		}
	}
}
