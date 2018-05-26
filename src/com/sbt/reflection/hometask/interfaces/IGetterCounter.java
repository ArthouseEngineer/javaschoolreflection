package com.sbt.reflection.hometask.interfaces;

/**
 * Интерфейс подсчитывающий количество гетттеров в классе
 */
public interface IGetterCounter {

    /**
     * Метод возвращаюший количество геттеров переданного класса
     * @param clazz класс в котором считаем геттеры
     * @return Количесво геттеров в переданном классе
     */
     int  calcGettersCount(Class<?> clazz);
}
