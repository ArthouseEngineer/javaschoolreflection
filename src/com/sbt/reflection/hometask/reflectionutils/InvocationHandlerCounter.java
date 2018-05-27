package com.sbt.reflection.hometask.reflectionutils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.sbt.reflection.hometask.interfaces.*;

public class InvocationHandlerCounter implements InvocationHandler {

    private IGetterCounter getterCounter;
    private Map<Class<?>,Integer> cacheMap = new HashMap<>();


    public InvocationHandlerCounter(IGetterCounter getterCounter) {
        this.getterCounter = getterCounter;
    }

    private int getCacheResult(Class<?> clazz){
        if(cacheMap.containsKey(clazz.getName())){
                return cacheMap.get(clazz.getName());
        }
        return -1;
    }


    private void putCacheResult(Class<?> clazz, int count){
        cacheMap.put(clazz,count);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        int count = getCacheResult(args[0].getClass());
        if (count == -1){
            count = (int) method.invoke(getterCounter,args[0]);
            putCacheResult(args[0].getClass(),count);
            return count;
        }
        return count;
    }
}
