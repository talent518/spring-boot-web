package com.talent518.demo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("urls", urls());
		model.addAttribute("baseUrl", request.getContextPath());
		model.addAttribute("paramMaps", paramMaps);
		return "index";
	}

	@Autowired
	WebApplicationContext applicationContext;
	private List<String> urls = new ArrayList<String>();
	private Map<String, String> paramMaps = new HashMap<String, String>();

	public List<String> urls() {
		if (urls.size() > 0)
			return urls;
		synchronized (urls) {
			if (urls.size() == 0) {
				RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
				// 获取url与类和方法的对应信息
				Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
				Set<String> set = new HashSet<String>();
				for (RequestMappingInfo info : map.keySet()) {
					StringBuilder sb = new StringBuilder();
					for (MethodParameter parameter : map.get(info).getMethodParameters()) {
						RequestParam annotation = parameter.getParameterAnnotation(RequestParam.class);
						if (annotation != null) {
							boolean hasDefValue = !ValueConstants.DEFAULT_NONE.equals(annotation.defaultValue());
							// System.out.println(parameter.getParameterIndex()+"=>"+parameter.getParameterName()+","+annotation.name()+",default:"+(hasDefValue?annotation.defaultValue():""));
							if (sb.length() > 0)
								sb.append(';');
							sb.append(annotation.name() + ":" + (hasDefValue ? annotation.defaultValue() : ""));
						}
					}

					// 获取url的Set集合，一个方法可能对应多个url
					Set<String> patterns = info.getPatternsCondition().getPatterns();
					for (String url : patterns) {
						// 把结果存入静态变量中程序运行一次次方法之后就不用再次请求次方法
						set.add(url);
						paramMaps.put(url, sb.toString());
						// System.out.println(url);
					}
				}
				set.remove("/");
				urls.addAll(set);
				urls.sort(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						return o1.compareTo(o2);
					}
				});
			}
		}

		return urls;
	}
}
