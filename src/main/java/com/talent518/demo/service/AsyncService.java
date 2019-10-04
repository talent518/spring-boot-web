package com.talent518.demo.service;

import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class AsyncService implements IAsyncService {
	private Random random = new Random();

	@Async
	@Override
	public Future<String> task1() throws Exception {
		System.out.println("task1 ...");
		long t = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long t2 = System.currentTimeMillis();
		System.out.println("task1 completed: " + (t2 - t) + " ms");
		return new AsyncResult<String>("task1 completed");
	}

	@Async
	@Override
	public Future<String> task2() throws Exception {
		System.out.println("task2 ...");
		long t = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long t2 = System.currentTimeMillis();
		System.out.println("task2 completed: " + (t2 - t) + " ms");
		return new AsyncResult<String>("task2 completed");
	}

	@Async
	@Override
	public Future<String> task3() throws Exception {
		System.out.println("task3 ...");
		long t = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long t2 = System.currentTimeMillis();
		System.out.println("task3 completed: " + (t2 - t) + " ms");
		return new AsyncResult<String>("task3 completed");
	}

}
