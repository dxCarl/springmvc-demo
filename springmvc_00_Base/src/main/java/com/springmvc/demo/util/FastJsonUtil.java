package com.springmvc.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/*
public static final Object parse(String text); // 把jsonStr文本parse为JSONObject或者JSONArray
public static final JSONObject parseObject(String text)； // 把jsonStr文本parse成JSONObject
public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean
public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合
public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
 */

public class FastJsonUtil {

	
	/**将jsonStr转换为JSONObj*/
	public static void jsonStr2JsonObj(String jsonStr) {
		JSONObject json = (JSONObject) JSON.parse(jsonStr);
		System.out.println("将jsonStr转换为JSONObj:{id:" + json.get("id") + ",name:" + json.get("name") + ",sex:" + json.get("sex") + ",age:" + json.get("age")+ "}");
	}
	
	/**将jsonStr转换为JavaBean*/
	public static void jsonStr2JavaBean(String jsonStr,Object obj) {
		Object user = JSON.parseObject(createUserStr(), Object.class);
		System.out.println("将jsonStr转换为JavaBean：" + user);
	}
	
	
	/**将JavaBean转换为JSONObject*/
	public static void javaBean2JsonObj(Object obj) {
		JSONObject json = (JSONObject) JSON.toJSON(obj);
		System.out.println("将JavaBean转换为JSONObj:{id:" + json.get("id") + ",name:" + json.get("name") + ",sex:" + json.get("sex") + ",age:" + json.get("age")+ "}");
	}
	
	/**将JavaBean转换为JsonStr*/
	public static String javaBean2JsonStr(Object obj) {
		String jsonStr = JSON.toJSONString(obj);
		System.out.println("将JavaBean转换为JsonStr:" + jsonStr);
		return jsonStr;
	}
	
	
	
	
	

	
	/*生成一个jsonStr*/
	private static String createUserStr() {
		//return  "{\"id\":1,\"name\":\"yucong\",\"age\":28,\"sex\":\"男\"}";
		//return "{}";
		return "abc";
	}
}
