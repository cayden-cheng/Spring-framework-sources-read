package org.framework.test;

import org.framework.dao.Config;
import org.framework.dao.Dao;
import org.framework.dao.MyBeanFactoryPostProcessor;
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
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		// 如果我们自己去实现了 BeanFactoryPostProcessor 则需要手动把当前类注册到
		// beanFactoryPostProcessors，否则不会在 invokeBeanFactoryPostProcessors 中解析
		ac.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());
		ac.register(Config.class, MyBeanFactoryPostProcessor.class);
		ac.refresh();
		SimpleBean simpleBean = ac.getBean(SimpleBean.class);
		simpleBean.send();
		Dao bean = ac.getBean(Dao.class);
		bean.print();
	}
}
