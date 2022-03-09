package ru.team.todo.injections;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class<?>, Object> beans = new HashMap<>();

    public ApplicationContext() {
    }

    public void addBean(Class<?> beanClass, Object beanInstance) {
        this.beans.put(beanClass, beanInstance);
        Class<?>[] instanceInterfaces = beanClass.getInterfaces();
        for (Class<?> instanceInterface : instanceInterfaces) {
            Object instance = getBean(instanceInterface);
            if (instance == null) {
                this.beans.put(instanceInterface, beanInstance);
            }
        }
    }

    public <T> T getBean(Class<T> c) {
        return (T) beans.get(c);
    }

}
