package org.framework.lambda;

/**
 * @author cayden
 * @version V1.0
 * @date 2020/12/7 14:01
 */
public interface TestInterface {

    default void printXXX(String inner){
        System.out.println("nothing");
    }
}
