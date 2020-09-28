package org.framework.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author cayden
 * @version V1.0
 * @date 2020/9/28 9:12
 * 当前实现的接口是 BeanFactoryPostProcessor 他还有一个子类 BeanDefinitionRegistryPostProcessor
 * 实现这个接口 拿出来的类都是已经实例化过后的bean
 * sh
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Nothing bean = beanFactory.getBean(Nothing.class);
        bean.print();
    }
}
