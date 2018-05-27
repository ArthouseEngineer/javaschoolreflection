package com.sbt.reflection.hometask.reflectionutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BeanUtils {
    public static Map<String, Method> getAllGetters(Object o) {

        Class<?> clazz = o.getClass();
        Map<String, Method> allGettersMap = new HashMap<>();

        Method[] methods = clazz.getDeclaredMethods();

        Matcher matcher;

        for (Method method : methods) {
            matcher = Pattern.compile("^get[A-Z]+").matcher(method.getName());
            if (matcher.find()) {
                if (method.getReturnType() != void.class && method.getParameterCount() == 0) {
                    allGettersMap.put(method.getName().substring(3), method);
                }
            }
        }
        return allGettersMap;
    }

    public static Map<String, Method> getAllSetters(Object o) {

        Class<?> clazz = o.getClass();
        Map<String, Method> allSettersMap = new HashMap<>();

        Matcher matcher;

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            matcher = Pattern.compile("^set[A-Z]+").matcher(method.getName());
            if (matcher.find()) {
                if (method.getReturnType() == void.class && method.getParameterCount() == 1)
                    allSettersMap.put(method.getName().substring(3), method);
            }
        }
        return allSettersMap;
    }

    static boolean isCompatible(Class<?> typeSetter, Class<?> typeGetter) {
        while (typeGetter != null) {
            if (typeSetter.equals(typeGetter)) {
                return true;
            }
            typeGetter = typeSetter.getSuperclass();
        }
        return false;
    }

    /**
     * Скнирует object "from" на наличие геттеры. Если object "to"
     * содержит соответсвующий геттеру сеттер, он вызовет его
     * для установки значения свойства для "to" равному значению свойства
     * объекта "from".
     * <p/>
     * Тип внутри сеттера должен быть совместимым с возвращаемым значением
     * геттера (если типы не являются однотипными вызова сеттера не происходит).
     * Совместимость означает, что тип параметра в сеттере должен
     * быть одинаковым или быть суперклассом возвращаемого типа геттера.
     * <p/>
     * Метод применяется только для публичных методов
     *
     * @param to   Объект чьй свойства необходимо установить.
     * @param from Объект со свойствами которые будут использованы для получения значений.
     */
    public static boolean assign(Object to, Object from) {
        Map<String, Method> allGettersMap = getAllGetters(from);
        Map<String, Method> allSettersMap = getAllSetters(to);

        for (Map.Entry<String, Method> settersMap : allSettersMap.entrySet()) {
            if (allGettersMap.containsKey(settersMap.getKey())) {
                Method setter = settersMap.getValue();
                Method getter = allGettersMap.get(settersMap.getKey());

                Class<?> setterType = setter.getParameterTypes()[0];
                Class<?> getterType = getter.getReturnType();

                if (isCompatible(setterType, getterType)) {
                    try {
                        setter.invoke(to, getter.invoke(from));
                        return true;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Исключение во время рефлексивного доступа к методу!");
                    }
                }
            }
        }
        return false;
    }
}

