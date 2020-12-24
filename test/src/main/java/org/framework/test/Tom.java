package org.framework.test;

/**
 * @author cayden
 * @version V1.0
 * @date 2020/10/13 13:40
 */
public class Tom implements TestInterface {


    @Override
    public void doExercise(String time) {
        System.out.println("Tom spend time do exervise" + time);
    }
}
