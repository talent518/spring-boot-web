package com.talent518.demo.service;

import java.util.concurrent.Future;

public interface IAsyncService {
	Future<String> task1() throws Exception;
	Future<String> task2() throws Exception;
	Future<String> task3() throws Exception;
}
