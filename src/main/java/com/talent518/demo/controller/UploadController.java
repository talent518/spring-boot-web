package com.talent518.demo.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@GetMapping("/upload")
	public String upload(HttpServletRequest request, Model model) {
		File[] files = new File(request.getServletContext().getRealPath("/upload")).listFiles();
		if (files != null && files.length > 0) {
			Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					return -Long.compare(o1.lastModified(), o2.lastModified());
				}
			});
			String[] names = new String[files.length];
			int i = 0;
			for(File f: files) {
				names[i++] = f.getName();
			}
			model.addAttribute("files", names);
		}
		model.addAttribute("baseUrl", request.getContextPath());
		return "upload";
	}

	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile file, HttpServletRequest request) throws Exception {
		String name = file.getOriginalFilename();
		String ext = name.substring(name.lastIndexOf('.')).toLowerCase();
		if (!ext.matches("\\.(png|gif|jpe?g|bmp|svg)")) {
			return "<script type=\"\">alert('只能上传文件类型为png,gif,jpg,jpeg,bmp,svg的图片！');history.back();</script>";
		}
		File path = new File(request.getServletContext().getRealPath("/upload"));
		if (!path.isDirectory()) path.mkdir();
		File f = File.createTempFile("test-", ext, path);
		file.transferTo(f);

		return "<img src=\"" + request.getContextPath() + "/upload/" + f.getName() + "\" style=\"max-width:100%;\" border=\"0\"/>";
	}
}
