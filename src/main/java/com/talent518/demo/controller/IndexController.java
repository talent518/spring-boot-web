package com.talent518.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class IndexController {
	@ResponseBody
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"	<title>spring-boot-web</title>\n" + 
				"</head>\n" + 
				"<body style=\"line-height:26px;font-size:14px;\">\n");
		for(String url: urls()) {
			if(url.indexOf('{') > -1)
				sb.append(url).append("<br/>\n");
			else
				sb.append(String.format("<a href=\"%s\">%s</a><br/>\n", request.getContextPath() + url, url));
		}
		sb.append("</body></html>");
		return sb.toString();
	}

	@Autowired
	WebApplicationContext applicationContext;
	private List<String> urls = new ArrayList<String>();

	public List<String> urls() {
		if(urls.size() > 0) return urls;
		synchronized (urls) {
			if(urls.size() == 0) {
				RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
				// 获取url与类和方法的对应信息
				Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
				Set<String> set = new HashSet<String>();
				for (RequestMappingInfo info : map.keySet()) {
					// 获取url的Set集合，一个方法可能对应多个url
					Set<String> patterns = info.getPatternsCondition().getPatterns();
					for (String url : patterns) {
						// 把结果存入静态变量中程序运行一次次方法之后就不用再次请求次方法
						set.add(url);
					}
				}
				set.remove("/");
				urls.addAll(set);
			}
		}
		
		return urls;
	}
}
