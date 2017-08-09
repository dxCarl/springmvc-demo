package com.springmvc.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.demo.model.User;
import com.springmvc.demo.util.Constant;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "redirect:/login.jsp";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response){
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		Constant.MY_LOG.debug("登录验证,userName:" + userName + ",password:" + password);
		
		Cookie cookie=new Cookie("user",userName+"-"+password);
		cookie.setMaxAge(1*60*60*24*7);
		
		User currentUser=new User(userName,password);
		response.addCookie(cookie);

		HttpSession session=request.getSession();
		session.setAttribute("currentUser", currentUser);
		return "redirect:/main.jsp";
	}
	
	@RequestMapping("/login2")
	public String login2(HttpServletRequest request){
		return "redirect:/main.jsp";
	}
	
	@RequestMapping("/login3")
	public String login3(HttpSession session){
		return "redirect:/main.jsp";
	}
	
}
