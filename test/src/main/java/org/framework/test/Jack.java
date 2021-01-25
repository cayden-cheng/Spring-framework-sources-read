package org.framework.test;

/**
 * @author cayden
 * @version V1.0
 * @date 2020/10/13 13:39
 */
public class Jack implements TestInterface {

    @Override
    public void doExercise(String time) {
        System.out.println("jack spend time do exervise" + time);
    }

}
