package com.multiracer.utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtility {

	private static ApplicationContext ctx;
	
	public static ApplicationContext getApplicationContext() {
		if(ctx == null)
			ctx = new ClassPathXmlApplicationContext(new String[] {""});
		return ctx;
	}
}
