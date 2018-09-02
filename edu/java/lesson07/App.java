package lesson07;

import lesson07.annotations.AfterSuite;
import lesson07.annotations.BeforeSuite;
import lesson07.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * @autor Kunakbaev Artem
 */
public class App {
    private static TreeMap<Priority, Method> tree = new TreeMap<>();
    private static boolean isBefore = true;
    private static boolean isAfter = true;
    private static Method before = null;
    private static Method after = null;

    public static void main(String[] args) {
        start(TestedClass.class);
    }

    private static void start(Class clazz){
        Method[] methods = clazz.getMethods();
        Priority priority;
        for (Method m : methods) {
            if(m.getAnnotation(BeforeSuite.class) != null){
                if(isBefore){
                    before = m;
                    isBefore = false;
                }else {
                    throw new RuntimeException("Аннотация BeforeSuite уже используется");
                }
            }
            if(m.getAnnotation(AfterSuite.class) != null){
                if(isAfter){
                    after = m;
                    isAfter = false;
                }else {
                    throw new RuntimeException("Аннотация AfterSuite уже используется");
                }
            }
            if(m.getAnnotation(Test.class) != null){
                priority = new Priority(m.getAnnotation(Test.class).priority(), m.getName());
                tree.put(priority, m);
            }
        }

        try {
            Object o = clazz.getConstructor().newInstance();
            before.invoke(o);
            for (Map.Entry<Priority, Method> entry : tree.entrySet()) {
                entry.getValue().invoke(o);
            }
            after.invoke(o);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
