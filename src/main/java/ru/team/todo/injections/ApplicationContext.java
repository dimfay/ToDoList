package ru.team.todo.injections;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class<?>, Object> beans = new HashMap<>();

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

    /**
     * Получить экземпляр класса со всеми внедренными зависимостями внутри.
     * @param clazz Класс который нужно получить в виде экземпляра с зависимостями
     * @return Экземпляр класса с внедренными зависимостями
     */
    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

}
