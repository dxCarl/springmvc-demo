                                                                                                                                                                                 package com.yucong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.demo.util.Constant;
import com.springmvc.demo.util.ResponseUtil;
import com.yucong.model.ReturnVO;
import com.yucong.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@RequestMapping(value="/ajax11",method=RequestMethod.POST)
	public void ajax11(HttpServletRequest request ,HttpServletResponse response,
			@RequestBody User user) throws Exception{
		Constant.MY_LOG.debug(user.toString());
		if(user.getUserName() != null) {//测试用，当userName有值时，抛出异常
			throw new RuntimeException("测试ExceptionHander注解的使用");
		}
		ResponseUtil.write(response, user);
	}
	
	@ExceptionHandler
	public @ResponseBody ReturnVO handlerException(HttpServletRequest request,Exception ex) {
		ReturnVO returnVO = new ReturnVO();
		if(ex instanceof RuntimeException) { //接口参数异常，不打印错误日志
			returnVO.setCode("ERROR");
			returnVO.setMessage(ex.getMessage());
		}
		return returnVO;
	}
	
}
