                                                                                                                                                                                 package com.springmvc.demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.springmvc.demo.model.User;
import com.springmvc.demo.util.Constant;
import com.springmvc.demo.util.ResponseUtil;


@Controller
@RequestMapping("/user")
public class UserController {

	
	@RequestMapping(value="/ajax",method=RequestMethod.GET)
	public @ResponseBody User ajax(){
		User user=new User("zhangsan","123");
		Constant.MY_LOG.debug("请求处理中...");
		return user;
	}
	
	@RequestMapping(value="/ajax2",method=RequestMethod.GET)
	public void ajax2(HttpServletResponse response) throws Exception{
		User user=new User("zhangsan","123");
		ResponseUtil.write(response, user);
	}
	
	@RequestMapping(value="/ajax3",method=RequestMethod.POST)
	public void ajax3(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		InputStream is = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String jsonStr = br.readLine();
		Constant.MY_LOG.debug("Post请求处理中,jsonStr: " + jsonStr);
		User u = JSON.parseObject(jsonStr, User.class);
		ResponseUtil.write(response, u);
	}
	
	//@RequestMapping(value="/ajax4",method=RequestMethod.POST)
	@RequestMapping(value="/ajax4")
	public void ajax4(HttpServletRequest request ,HttpServletResponse response, @RequestBody User user) throws Exception{
		response.addHeader("Access-Control-Allow-Origin", "*");
		if(user == null) {
			Constant.MY_LOG.debug("user is null");
		} else {
			Constant.MY_LOG.debug("user is "  + user.toString());
		}
		ResponseUtil.write(response, user);
	}
	
}
