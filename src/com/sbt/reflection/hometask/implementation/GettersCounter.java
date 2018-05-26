package com.sbt.reflection.hometask.implementation;

import com.sbt.reflection.hometask.interfaces.*;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sbt.reflection.hometask.annotations.*;

public class GettersCounter implements IGetterCounter {

    @Override
    public int calcGettersCount(Class<?> clazz) {

        int count = 0;
        Method[] methods = clazz.getDeclaredMethods();

        Matcher matcher;

        for (Method method : methods) {
            matcher = Pattern.compile("^get[A-Z]+").matcher(method.getName());
            if (matcher.find() && method.getAnnotation(Skip.class) == null) {
                count++;
            }
        }
        return count;
    }
}
