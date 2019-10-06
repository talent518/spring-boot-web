package com.talent518.demo.websocket.pojo;

public class SocketMessage {
	private String group;
	private String name;
	private String message;

	public SocketMessage(String group, String name, String message) {
		super();
		this.group = group;
		this.name = name;
		this.message = message;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
