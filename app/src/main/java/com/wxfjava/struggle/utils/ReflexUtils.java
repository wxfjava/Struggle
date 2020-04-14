package com.wxfjava.struggle.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflexUtils {

    public static Object getProperty(Object owner, String fieldName) throws Exception {
        Field f = getField(owner.getClass(), fieldName);
        return f != null ? f.get(owner) : null;
    }

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
        } catch (Exception exp) {
            Class<?> base = clazz.getSuperclass();
            if (base != null)
                method = getMethod(base, methodName, parameterTypes);
        }
        return method;
    }

    public static Field getField(Class<?> cls, String fieldName) {
        Field field = null;
        try {
            field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (Exception exp) {
            Class<?> base = cls.getSuperclass();
            if (base != null)
                field = getField(base, fieldName);
        }
        return field;
    }

    public static boolean setProperty(Object owner, String fieldName, Object value) {

        try {
            Field field = getField(owner.getClass(), fieldName);
            field.set(owner, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
