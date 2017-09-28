package com.yucong.demo.util;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

public class LogAppender extends DailyRollingFileAppender {

	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		 boolean returnValue = false;
		 //只判断是否相等，而不判断优先级 
		 //返回true时，才进行输出，就实现了真正Log4j按照级别输出日志文件。
		if(this.getThreshold() == null ) {
			returnValue = true;
		} else {
			returnValue =  this.getThreshold().equals(priority);
		}
		return returnValue;
	}
	
	
	
}
