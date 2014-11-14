package org.summer.ioc.beans;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.summer.ioc.annotation.IceCream;
import org.summer.ioc.annotation.Santa;

import static org.summer.utils.Print.print;

public enum BeanFactory {

    INSTANCE;
    public BeanFactory getInstance(String[] className) {
        injection(className);
        return INSTANCE;
    }

    public Object getBean(String beanName) {
        Object object = (Object) beans.get(beanName);
        return object;
    }

    private void injection(String[] className) {
        try {
            // ×¢²ábean
            for (String name : className) {
                registerBean(Class.forName(name));
            }
            // ×¢ÈëÊôÐÔ
            for (String name : className) {
                injectProperty(Class.forName(name));
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void registerBean(Class<?> clazz) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        IceCream iceCream = (IceCream) clazz.getAnnotation(IceCream.class);
        if (iceCream != null) {
            try {
                // ×¢²ábean
                beans.put(iceCream.value(), clazz.newInstance());
                print("register " + iceCream.value());
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void injectProperty(Class<?> clazz)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        IceCream iceCream = (IceCream) clazz.getAnnotation(IceCream.class);
        if (iceCream == null) {
            return;
        }
        for (Field field : clazz.getDeclaredFields()) {
            Santa santa = field.getAnnotation(Santa.class);
            if (santa != null) {
                String heyName = field.getName();
                // ×¢ÈëÊôÐÔ
                Object object = beans.get(heyName);
                field.setAccessible(true);
                field.set(beans.get(iceCream.value()), object);
                print("inject " + iceCream.value() + "." + heyName);
            }
        }
    }

    private Map<String, Object> beans = new HashMap<String, Object>();
}
