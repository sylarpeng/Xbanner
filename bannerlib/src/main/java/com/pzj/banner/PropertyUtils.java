package com.pzj.banner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 属性反射工具
 */
public class PropertyUtils {

    public PropertyUtils() {
    }


    public static Annotation[] getFieldAnnotations(Class<?> clazz, String name) {
        try {
            Field f = clazz.getDeclaredField(name);
            Annotation[] annotations = f.getDeclaredAnnotations();
            return annotations;
        } catch (NoSuchFieldException var4) {
            Class<?> superClazz = clazz.getSuperclass();
            if (!superClazz.getName().equals("java.lang.Object")) {
                return getFieldAnnotations(superClazz, name);
            }

            System.out.println("No such field {}: {}" + name + var4.getMessage());
        } catch (NullPointerException var5) {
            System.out.println("Null Exception {}: {}"+ name + var5.getMessage());
        }

        return null;
    }

    public static Annotation getAnnotation(Object object, String name, Class<? extends Annotation> clazz) {
        try {
            Field f = object.getClass().getDeclaredField(name);
            return f.getAnnotation(clazz);
        } catch (NoSuchFieldException var4) {
            System.out.println("{}"+ var4);
        } catch (NullPointerException var5) {
            System.out.println("Null Exception {}: {}"+ name+ var5.getMessage());
        }

        return null;
    }


}
