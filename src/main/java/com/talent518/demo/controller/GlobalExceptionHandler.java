package com.talent518.demo.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent518.demo.entity.Result;

// 全局异常处理器
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	Result handlerException(Throwable e) {
		e.printStackTrace();
		if (e instanceof DuplicateKeyException) {
			String s = e.getMessage();
			String s2 = s.substring(s.lastIndexOf("Duplicate"));
			return new Result(s2);
		}
		return new Result(e.getMessage());
	}
}
