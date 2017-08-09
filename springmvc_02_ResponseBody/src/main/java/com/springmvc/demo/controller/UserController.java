package com.springmvc.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.demo.model.User;
import com.springmvc.demo.util.Constant;
import com.springmvc.demo.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public @ResponseBody User ajax() {
		Constant.MY_LOG.debug("请求测试ajax");
		User user = new User("zhangsan", "123");
		return user;
	}

	@RequestMapping(value = "/ajax2", method = RequestMethod.GET)
	public void ajax2(HttpServletResponse response) throws Exception {
		Constant.MY_LOG.debug("请求测试ajax2");
		User user = new User("zhangsan", "123");
		ResponseUtil.write(response, user);
	}

}
