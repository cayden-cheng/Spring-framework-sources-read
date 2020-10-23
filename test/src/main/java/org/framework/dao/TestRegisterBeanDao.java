package org.framework.dao;

import org.springframework.stereotype.Component;

/**
 * @author cayden
 * @version V1.0
 * @date 2020/10/23 10:51
 */
@Component
public class TestRegisterBeanDao {

    public void sayHello(String name){
        System.out.println("Hello " + name);
    }
}
