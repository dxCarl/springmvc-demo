package com.springmvc.demo.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class ResponseUtil {

	public static void write(HttpServletResponse response,Object obj)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(FastJsonUtil.javaBean2JsonStr(obj));
		out.flush();
		out.close();
	}
}
