package com.springmvc.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.demo.util.Constant;

public class MyInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		String method = request.getMethod();
		
		if(method.equals("OPTIONS")) {
			Constant.MY_LOG.info("method is OPTIONS");
		} else {
			Constant.MY_LOG.info("method:" + method);
		}
		
		String requestUrl = request.getRequestURI(); 
		Constant.MY_LOG.info("===>MyInterceptor处理前:" + requestUrl);
		
		return false;//返回为true，会被下一个拦截器拦截
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		Constant.MY_LOG.debug("===>MyInterceptor处理中");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
		Constant.MY_LOG.debug("===>MyInterceptor处理后");
	}

}
