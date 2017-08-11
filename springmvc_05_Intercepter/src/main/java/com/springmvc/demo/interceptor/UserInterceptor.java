package com.springmvc.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.demo.util.Constant;

public class UserInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		String requestUrl = request.getRequestURI(); 
		Constant.MY_LOG.info("===>UserInterceptor处理前:" + requestUrl);
		
		/*InputStream is = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String jsonStr = br.readLine();
		Constant.MY_LOG.debug("jsonStr: " + jsonStr);*/
		
		return true;//返回为true，会被下一个拦截器拦截
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		Constant.MY_LOG.debug("===>UserInterceptor处理中");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
		Constant.MY_LOG.debug("===>UserInterceptor处理后");
	}

}
