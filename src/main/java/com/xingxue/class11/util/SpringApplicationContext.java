package com.xingxue.class11.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * SpringApplicationContext
 */
@Component
public class SpringApplicationContext implements ApplicationContextAware {
	
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringApplicationContext.context = context;
	}
	
	/**
	 * getBean
	 * @param beanId	bean id
	 * @return	bean实例
	 */
	public static <T> T getBean(String beanId) {
		return (T) context.getBean(beanId);
	}
	
	/**
	 * getBean
	 * @param clazz	bean类型
	 * @return	bean实例
	 */
	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

}
