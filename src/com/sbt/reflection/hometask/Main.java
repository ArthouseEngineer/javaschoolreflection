package com.sbt.reflection.hometask;


import com.sbt.reflection.hometask.implementation.*;
import com.sbt.reflection.hometask.interfaces.*;
import com.sbt.reflection.hometask.testclasess.*;
import com.sbt.reflection.hometask.reflectionutils.*;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        TestClass1 testClass1 = new TestClass1("Print", "Some", "Bla bla bla");
        TestClass1 copytestClass1 = new TestClass1();

        BeanUtils.assign(copytestClass1, testClass1);

        System.out.println("Fields value of  " + testClass1.getClass() +
                " field 1 is : " + testClass1.getField1() + " field 2 is : " + testClass1.getField2() +
                " field 3 is : " + testClass1.getField3() + "\n");


        System.out.println("Fields value of  " + copytestClass1.getClass() +
                " field 1 is : " + copytestClass1.getField1() + " field 2 is : " + copytestClass1.getField2() +
                " field 3 is : " + copytestClass1.getField3() + "\n");


        GettersCounter gettersCounter = new GettersCounter();


        System.out.println("Count getters on : " + TestAnnotationClass.class + " is :" +
                gettersCounter.calcGettersCount(TestAnnotationClass.class) + "\n");


        System.out.println("Count getters on : " + TestClass1.class + " is :" +
                gettersCounter.calcGettersCount(TestClass1.class) + "\n");


        IGetterCounter getterCounter2 = (IGetterCounter) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{IGetterCounter.class},
                new InvocationHandlerCounter(gettersCounter));


        System.out.println(getterCounter2.calcGettersCount(TestClass1.class));

    }


}
