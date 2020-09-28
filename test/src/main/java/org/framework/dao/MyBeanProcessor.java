package org.framework.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author cayden
 * @version V1.0
 * @date 2020/9/28 8:54
 *
 * 实现spring 提供的 BeanPostProcessor 接口，
 * 可以干预 spring bean 初始化生命周期，可用于功能，aop 代理
 *
 */
@Component
public class MyBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("dao")){
            System.out.println("nothing init before");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("dao")){
            System.out.println("nothing init after");
        }
        return bean;
    }
}
