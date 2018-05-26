package com.sbt.reflection.hometask;


import com.sbt.reflection.hometask.implementation.*;


import com.sbt.reflection.hometask.testclasess.*;

public class Main {

    public static void main(String[] args) {

        Class<?> testClass = TestClass1.class;

        GettersCounter gettersCounter = new GettersCounter();
        System.out.println(gettersCounter.calcGettersCount(TestAnnotationClass.class));
    }

}
