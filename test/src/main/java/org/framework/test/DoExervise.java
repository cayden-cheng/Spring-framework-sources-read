package org.framework.test;

/**
 * @author cayden
 * @version V1.0
 * @date 2020/10/13 13:40
 */
public class DoExervise {

    public void doExercise(TestInterface testInterface,String time){
        testInterface.doExercise(time);
    }

    public static void main(String[] args) {
        DoExervise doExervise = new DoExervise();
        Jack jack = new Jack();
        doExervise.doExercise(jack,"五分钟");
    }

}
