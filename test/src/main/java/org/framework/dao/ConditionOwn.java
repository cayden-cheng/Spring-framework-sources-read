package org.framework.dao;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author cayden
 * @version V1.0
 * @date 2020/10/22 14:10
 */
public class ConditionOwn implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 上下文需要的 context
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 运行环境
        Environment environment = context.getEnvironment();
        if (environment.getProperty("os.name").contains("Windows")){
            return true;
        }

        return false;
    }
}
