package com.pzj.banner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * analysis
 * 重复注解无用
 *
 * @create 2017-11-28 9:59
 **/
public class EntityAnalysis<T>  {

    public final HashMap<Class,Field> analysis = new HashMap<>();


    public static  EntityAnalysis init(Class cls,Class ... annotationClass){
        EntityAnalysis entityAnalysis = new EntityAnalysis();

        for (Class c: annotationClass){
            Field field = entityAnalysis.getFieldAnntation(cls,c);
            if (field !=null) {
                entityAnalysis.analysis.put(c, field);
            }
        }
        return  entityAnalysis;
    }


    /**
     * 获取 属性上注解等于 指定的class的field
     * @param cl
     * @param annotationClass
     * @return
     */
    public Field getFieldAnntation(Class cl,Class<Annotation> annotationClass){
        for (Field field : cl.getDeclaredFields()) {
            field.setAccessible(true);
            Annotation[] annotations = PropertyUtils.getFieldAnnotations(cl, field.getName());
            if (annotations !=null) {
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().equals(annotationClass)) {
                            return  field;
                    }
                }
            }
        }
        return  null;
    }


    /**
     * 根据注解获取值
     * @param annotationClass
     * @return
     */
    public Object getValue(T entity,Class<Annotation> annotationClass){
        Field field = analysis.get(annotationClass);
        if (field !=null){
            try {
                field.setAccessible(true);
                return  field.get(entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

}
