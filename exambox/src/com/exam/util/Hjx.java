package com.exam.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exam.biz.ErrorBiz;

public class Hjx {

	public static void main(String[] args) {
		try{
			 ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			 ErrorBiz errorBiz = (ErrorBiz) ac.getBean("errorBiz");
			 errorBiz.findErrorBySub(1);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
