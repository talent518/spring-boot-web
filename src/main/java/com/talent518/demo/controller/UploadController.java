package com.talent518.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class UploadController {
	public class FileItem {
		private String img;
		private String lnk;
		
		public FileItem(String img, String lnk) {
			super();
			this.img = img;
			this.lnk = lnk;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public String getLnk() {
			return lnk;
		}
		public void setLnk(String lnk) {
			this.lnk = lnk;
		}
	}
	
	@GetMapping("/upload")
	public String upload(HttpServletRequest request, Model model) throws IOException {
		File[] files = new File(request.getServletContext().getRealPath("/upload")).listFiles();
		if (files != null && files.length > 0) {
			Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					return -Long.compare(o1.lastModified(), o2.lastModified());
				}
			});
			List<FileItem> items = new ArrayList<FileItem>();
			for(File f: files) {
				if(f.getName().contains("-120x120.") || !f.getName().matches(".*\\.(png|gif|jpe?g|bmp|svg)$")) continue;
				int pos = f.getName().lastIndexOf('.');
				File f2 = new File(f.getParent(), f.getName().substring(0, pos) + "-120x120" + f.getName().substring(pos));
				if(!f2.exists()) Thumbnails.of(f).size(120, 120).keepAspectRatio(true).toFile(f2);
				items.add(new FileItem(f2.getName(), f.getName()));
			}
			model.addAttribute("files", items);
		}
		model.addAttribute("baseUrl", request.getContextPath());
		return "upload";
	}

	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile file, HttpServletRequest request) throws Exception {
		if(file.isEmpty()) return "<script type=\"text/javascript\">alert('请选择文件类型为png,gif,jpg,jpeg,bmp,svg的图片！');history.back();</script>";
		String name = file.getOriginalFilename();
		String ext = name.substring(name.lastIndexOf('.')).toLowerCase();
		if (!ext.matches("\\.(png|gif|jpe?g|bmp|svg)")) {
			return "<script type=\"text/javascript\">alert('只能上传文件类型为png,gif,jpg,jpeg,bmp,svg的图片！');history.back();</script>";
		}
		File path = new File(request.getServletContext().getRealPath("/upload"));
		if (!path.isDirectory()) path.mkdir();
		File f = File.createTempFile("test-", ext, path);
		file.transferTo(f);

		return "<img src=\"" + request.getContextPath() + "/upload/" + f.getName() + "\" style=\"max-width:100%;\" border=\"0\"/>";
	}
}
