package com.springmvc.demo.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	/**单文件上传*/
	@RequestMapping("/upload")
	public String uploadFile(@RequestParam("file1") MultipartFile file1,HttpServletRequest request)throws Exception{
		String filePath=request.getServletContext().getRealPath("/");
		System.out.println(filePath);
		file1.transferTo(new File(filePath+"/upload/"+file1.getOriginalFilename()));
		return "redirect:success.html";
	}
	
	
	/**多文件上传*/
	@RequestMapping("/upload2")
	public String uploadFiles(@RequestParam("file") MultipartFile[] files,HttpServletRequest request)throws Exception{
		String filePath=request.getServletContext().getRealPath("/");
		System.out.println(filePath);
		for(MultipartFile file:files){
			file.transferTo(new File(filePath+"/upload/"+file.getOriginalFilename()));			
		}
		return "redirect:success.html";
	}
}
