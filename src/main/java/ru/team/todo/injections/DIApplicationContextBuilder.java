package ru.team.todo.injections;

import java.util.List;

public class DIApplicationContextBuilder {

    private final ClassFinder classFinder = new ClassFinder();
    private final DIComponentFilter componentFilter = new DIComponentFilter();
    private final DIComponentCreator componentsCreator = new DIComponentCreator();
    private final DIDependencyResolver dependencyResolver = new DIDependencyResolver();

    public ApplicationContext build(String packageName) {
        try {
            List<Class<?>> allPackageClasses = this.classFinder.findClassesInsidePackage(packageName);
            List<Class<?>> diComponents = this.componentFilter.filter(allPackageClasses);

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