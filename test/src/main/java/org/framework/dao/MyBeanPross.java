package org.framework.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author cayden
 * @date 2020/9/27 22:10
 */
@Component
public class MyBeanPross implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition dao = beanFactory.getBeanDefinition("simpleBean");
		dao.setScope("prototype");
	}
}
