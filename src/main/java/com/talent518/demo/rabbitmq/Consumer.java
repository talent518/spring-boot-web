package com.talent518.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	@RabbitHandler
	@RabbitListener(queues = "schedule")
	public void consumer1(String message) {
		System.out.println("RabbitMQ of 'schedule' Queue: " + message);
	}

	@RabbitHandler
	@RabbitListener(queues = "web")
	public void consumer2(String message) {
		System.out.println("RabbitMQ of 'web' Queue: " + message);
	}
}
