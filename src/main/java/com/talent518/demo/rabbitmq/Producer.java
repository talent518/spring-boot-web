package com.talent518.demo.rabbitmq;

import java.util.Base64;
import java.util.Random;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Producer {
	@Autowired
	private AmqpTemplate amqpTemplate;

	public void send(String routingKey, Object message) {
		amqpTemplate.convertAndSend(routingKey, message);
	}

	Random random = new Random();
	byte[] randomBuffer = new byte[64];
	boolean isScheduleOff = true;

	@Scheduled(cron = "*/1 * * * * ?")
	private void schedule() {
		if(isScheduleOff) return;
		
		random.nextBytes(randomBuffer);
		send("schedule", Base64.getEncoder().encodeToString(randomBuffer));
	}

	public boolean toggleSchedule() {
		isScheduleOff = !isScheduleOff;
		return isScheduleOff;
	}
}
