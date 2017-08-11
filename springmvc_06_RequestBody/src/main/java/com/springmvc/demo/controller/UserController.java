                                                                                                                                                                                 package com.springmvc.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.demo.model.User;
import com.springmvc.demo.util.Constant;
import com.springmvc.demo.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 
	 * 当控制器处理方法使用到@RequestBody时，Spring首先根据请求头的Accept属性选择匹配的HttpMessageConverter,
	 * 进而根据参数类型或泛型类型的过滤得到匹配的HttpMessageConverter;
	 * 
	 * 然后将请求消息转换并绑定到该入参中。
	 * 
	 * {"id":11,"userName":"yucong1","password":"haozhuo20151"}
	 * 可自动被User对象接收【Content-Type:application/json】
	 * 
	 * 先根据Http头中的Content-Type匹配HttpMessageConverter【MappingJacksonHttpMessageConverter】,
	 * 然后将Json字符串自动转换成User对象。
	 * 
	 * MappingJacksonHttpMessageConverter
	 * 用途：利用Jackson开源包的ObjectMapper读写Json数据。
	 * 1) 可读写application/json类型的数据
	 * 2) 响应信息的媒体类型为application/json 
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping(value="/ajax4",method=RequestMethod.POST)
	public void ajax4(HttpServletRequest request ,HttpServletResponse response,@RequestBody User user) throws Exception{
		Constant.MY_LOG.debug(user.toString());
		ResponseUtil.write(response, user);
	}
	
	
}
