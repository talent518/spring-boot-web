package com.talent518.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Result {
	private boolean status = true;
	private String message = "OK";
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer total;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;

	public static Result ok() {
		return new Result();
	}

	public Result() {
	}

	public Result(String message) {
		this.status = false;
		this.message = message;
	}

	public Result(Object data) {
		this.data = data;
	}

	public Result(Integer total, Object data) {
		super();
		this.total = total;
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.status = false;
		this.message = message;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result{status: " + status + ", message: " + message + ", total: " + total + ", data: " + data + "}";
	}
}
