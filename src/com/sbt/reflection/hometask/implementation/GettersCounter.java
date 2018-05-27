package com.sbt.reflection.hometask.implementation;

import com.sbt.reflection.hometask.interfaces.*;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sbt.reflection.hometask.annotations.*;

public class GettersCounter implements IGetterCounter {

    @Override
    public int calcGettersCount(Class<?> clazz) {

        int count = 0;

        if (clazz == null)
            return 0;
        Method[] methods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();

        List<String> fieldsName = new ArrayList<>();

        for (Field field : fields)
            fieldsName.add(field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));

        Matcher matcher;

        for (Method method : methods) {
            matcher = Pattern.compile("^get[A-Z]+").matcher(method.getName());
            if (matcher.find() && method.getAnnotation(Skip.class) == null
                    && fieldsName.contains(method.getName().substring(3))) {
                count++;
            }
        }
        return count;
    }
}
