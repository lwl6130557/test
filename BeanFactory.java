package com.lwl.spring.day01.factory;


import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class BeanFactory {
	//定义一个 properties 对象
	private  static Properties props;
	//优化 改造 定义map容器 用于存放我们要创建的 对象
	private static Map<String ,Object> beans;
	
	static {
		try {
		props = new Properties();
		
		InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
		props.load(in);
		//实例化容器
		beans=new HashMap<String,Object>();
		//取出配置文件里所有的 key
		Enumeration keys = props.keys();
		//遍
		while (keys.hasMoreElements()){
			String key = keys.nextElement().toString();
			//根据key 来获取value
			String beanpath = props.getProperty(key);
			//反射创建对象
			Object value=Class.forName(beanpath).newInstance();
			beans.put(key,value);
			}
		}catch (Exception e) {
			throw new ExceptionInInitializerError("初始化pro错误");
		}
	}
	
	


	
	 public static Object getBean(String beanName){
		 
		 return beans.get(beanName);
//		Object bean=null;
//		try {
//		String beanPath = props.getProperty(beanName);
//		//反射获取 到
//		bean = Class.forName(beanPath).newInstance();
//		}catch (Exception e){
//		e.printStackTrace();
//		}
//		return bean;
		}
		


	
}
