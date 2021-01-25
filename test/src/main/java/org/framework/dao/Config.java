package org.framework.dao;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author cayden
 * @date 2020/6/2 23:22
 */
@Configuration
@ComponentScan("org.framework.dao")
@Description("nothing")
public class Config {

    @Bean
    //@Conditional(ConditionOwn.class)
    public TestBean createTestBean(){
        return new TestBean();
    }

}
