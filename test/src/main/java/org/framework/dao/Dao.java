package org.framework.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author cayden
 * @date 2020/3/16 15:09
 * 注意，如果实现了BeanFactoryPostProcessor 或者他的子类接口 BeanDefinitionRegistryPostProcessor
 * 在实现 BeanPostProcessor 的时候，是不能取到当前类的
 */
@Component
public class Dao implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 实现 beanFactoryPostProcessor 可以拿到bean的定义然后干预 spring bean 的生命周期
        BeanDefinition dao = beanFactory.getBeanDefinition("nothing");
        System.out.println(dao.isSingleton());
    }

    public void print(){
        System.out.println("i'm dao !");
    }
}
