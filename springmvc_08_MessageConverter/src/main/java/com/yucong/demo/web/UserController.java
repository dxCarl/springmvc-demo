package com.yucong.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yucong.demo.UserService;
import com.yucong.demo.domain.User;
import com.yucong.demo.util.FastJsonUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "/handle51")
	@ResponseBody
	public User handle51(@Valid User user,BindingResult result) {
		System.err.println(result.hasErrors());
		System.err.println(FastJsonUtil.toJson(user));
		user.setUserId("1000");
		return user;
	}



}
