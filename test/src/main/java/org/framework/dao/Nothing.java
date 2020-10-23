package org.framework.dao;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author cayden
 * @version V1.0
 * @date 2020/9/28 9:03
 */
@Component
@Order(value = 0)
public class Nothing {

    public void print(){
        System.out.println("nothing");
    }
}
