package com.talent518.demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent518.demo.entity.Result;

// 全局异常处理器
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	Result handlerException(Exception e) {
		return new Result(e.getMessage());
	}
}
