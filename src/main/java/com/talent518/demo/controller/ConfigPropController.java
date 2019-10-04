package com.talent518.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ConfigurationProperties(prefix = "config.prop")
@Controller
public class ConfigPropController {
	@Value("${config.value}")
	private String configValue;
	
	private String uploadDir;
	private String uploadUrl;
	private int width;
	private int height;
	private String desc;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@ResponseBody
	@RequestMapping("/config-prop")
	public String show() {
		return "configValue: " + configValue + "<br/>\n" +
				"uploadDir: " + uploadDir + "<br/>\n" +
				"uploadUrl: " + uploadUrl + "<br/>\n" +
				"width: " + width + "<br/>\n" +
				"height: " + height + "<br/>\n" +
				"desc: " + desc;
	}
}
