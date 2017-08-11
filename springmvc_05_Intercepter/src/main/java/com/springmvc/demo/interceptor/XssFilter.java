package com.springmvc.demo.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springmvc.demo.util.Constant;

public class XssFilter implements Filter {

	@Override
	public void destroy() {
		// to nothing
	}

	 @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 
		 HttpServletResponse httpResponse = (HttpServletResponse) response;
	     httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		 
		 
		// ((Object) response).addHeader("Access-Control-Allow-Origin", "*");
		 
		 
		 
		 Constant.MY_LOG.debug("doFilter..." );
		XssHttpServletRequestWraper xssRequest = new XssHttpServletRequestWraper((HttpServletRequest) request);
		
		 String body = HttpHelper.getBodyString(xssRequest);
		
		 Constant.MY_LOG.debug("body:" + body);
		
		chain.doFilter(xssRequest, response);
    }  

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// do nothing
	}

}
