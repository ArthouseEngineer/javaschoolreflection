package com.sbt.reflection.hometask.reflectionutils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BeanUtils {
    public static Map<String, Method> getAllGetters(Class<?> clazz) {
        Map<String, Method> allGettersMap = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();

        Matcher matcher;

        for (Method method : methods) {
            matcher = Pattern.compile("^get[A-Z]+").matcher(method.getName());
            if (matcher.find()) {
                if (method.getReturnType() != void.class && method.getParameterCount() == 0) {
                    allGettersMap.put(method.getName(), method);
                }
            }
        }
        return allGettersMap;
    }
}
