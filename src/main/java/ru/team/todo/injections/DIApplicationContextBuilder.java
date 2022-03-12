package ru.team.todo.injections;

import java.util.List;

public class DIApplicationContextBuilder {

    private final DIComponentCreator componentsCreator = new DIComponentCreator();
    private final DIDependencyResolver dependencyResolver = new DIDependencyResolver();

    /**
     * Создать контекст приложения, в котором находятся все созданные экземпляры классов с внедренными зависимостями.
     * @param packageName Путь к главному пакету, от которого нужно найти все классы и к каждому создать и внедрить зависимости.
     * @return Контекст приложения со всеми внедренными зависимостями
     */
    public ApplicationContext build(String packageName) {
        try {
            List<Class<?>> allPackageClasses = ReflectionUtils.findClassesInsidePackage(packageName);
            List<Class<?>> diComponents = ReflectionUtils.filterAnnotation(allPackageClasses, DIComponent.class);

            ApplicationContext applicationContext = new ApplicationContext();
            this.componentsCreator.create(applicationContext, diComponents);
            this.dependencyResolver.resolve(applicationContext, diComponents);

            return applicationContext;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}