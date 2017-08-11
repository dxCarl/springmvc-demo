package com.yucong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

	@RequestMapping(value="/helloWorld",method=RequestMethod.GET)
	public String helloWorld(Model model){
	model.addAttribute("message", "Hello SpringMvc!--测试");
		return "helloWorld";
	}
}
