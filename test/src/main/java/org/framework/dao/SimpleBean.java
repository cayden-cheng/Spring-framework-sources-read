package org.framework.dao;

import org.springframework.stereotype.Component;

/**
 * @author chengliang
 * @date 2020/3/18 14:02
 */
@Component
public class SimpleBean {
	public void send() {
		System.out.println("I am send method from SimpleBean!");
	}
}
