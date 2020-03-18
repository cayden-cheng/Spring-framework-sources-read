package org.framework.test;

import org.framework.dao.Dao;
import org.framework.dao.SimpleBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chengliang
 * @date 2020/3/16 15:03
 */
public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:config.xml");
		SimpleBean bean = context.getBean(SimpleBean.class);
		bean.send();
		context.close();
	}
}
