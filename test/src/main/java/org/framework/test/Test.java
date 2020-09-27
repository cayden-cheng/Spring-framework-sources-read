package org.framework.test;

import org.framework.dao.Config;
import org.framework.dao.SimpleBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author chengliang
 * @date 2020/3/16 15:03
 */
public class Test {

	public static void main(String[] args) {
		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:config.xml");
		SimpleBean bean = context.getBean(SimpleBean.class);
		bean.send();
		context.close();*/
		//notthing
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
		SimpleBean simpleBean = ac.getBean(SimpleBean.class);
		SimpleBean simpleBean1 = ac.getBean(SimpleBean.class);
		simpleBean.send();
		System.out.println(simpleBean.hashCode() + "------------------" + simpleBean1.hashCode());
	}
}
