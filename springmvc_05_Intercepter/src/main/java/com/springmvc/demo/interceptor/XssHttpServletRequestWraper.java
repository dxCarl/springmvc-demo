package com.springmvc.demo.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.springmvc.demo.util.Constant;
import com.springmvc.demo.util.StringUtil;


public class XssHttpServletRequestWraper extends HttpServletRequestWrapper {

	private final byte[] body;
	
	public XssHttpServletRequestWraper(HttpServletRequest request) throws IOException {
		super(request);
		
		/*InputStream is = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String jsonStr = br.readLine();
		Constant.MY_LOG.debug("jsonStr: " + jsonStr);*/
		body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
		Constant.MY_LOG.debug("jsonStr: " + new String(body));
	}
	
	@Override
	public String getParameter(String name) {
		Constant.MY_LOG.debug("getParameter----->转义处理");
		//return clearXss(super.getParameter(name));// 保留勿删
		return xssEncode(super.getParameter(name));
	}
	
	@Override
	public String getHeader(String name) {
		Constant.MY_LOG.debug("getHeader----->转义处理");
		//return clearXss(super.getHeader(name)); // 保留勿删
		return xssEncode(super.getParameter(name));
	}
	
	@Override
	public String[] getParameterValues(String name) {
		Constant.MY_LOG.debug("getParameterValues----->转义处理");
		if(!StringUtil.isEmpty(name)){
			String[] values = super.getParameterValues(name);
			if(values != null && values.length > 0){
				String[] newValues = new String[values.length];
				
				for(int i =0; i< values.length; i++){
					//newValues[i] = clearXss(values[i]);// 保留勿删
					newValues[i] = xssEncode(values[i]);
				}
				return newValues;
			}
		}
		return null;
	}

	/**
	 *  
	 * 处理字符转义【勿删，请保留该注释代码】
	 * @param value
	 * @return
	private String clearXss(String value){
		if (value == null || "".equals(value)) {
			return value;
		}
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replace("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replace("script", "");
		return value;
	}*/
	
	/** 
     * 将特殊字符替换为全角 
     * @param s 
     * @return 
     */  
    private  String xssEncode(String s) {  
        if (s == null || s.isEmpty()) {  
            return s;  
        }  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            switch (c) {  
            case '>':  
                sb.append('＞');// 全角大于号  
                break;  
            case '<':  
                sb.append('＜');// 全角小于号  
                break;  
            case '\'':  
                sb.append('‘');// 全角单引号  
                break;  
            case '\"':  
                sb.append('“');// 全角双引号  
                break;  
            case '&':  
                sb.append('＆');// 全角＆  
                break;  
            case '\\':  
                sb.append('＼');// 全角斜线  
                break;  
            case '/':  
                sb.append('／');// 全角斜线  
                break;  
            case '#':  
                sb.append('＃');// 全角井号  
                break;  
            case '(':  
                sb.append('（');// 全角(号  
                break;  
            case ')':  
                sb.append('）');// 全角)号  
                break;  
            default:  
                sb.append(c);  
                break;  
            }  
        }  
        return sb.toString();  
    } 
    
    
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
    
    
}
