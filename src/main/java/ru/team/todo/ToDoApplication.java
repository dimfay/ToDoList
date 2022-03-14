package ru.team.todo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.team.todo.ui.Menu;

public class ToDoApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ToDoConfiguration.class);

        var menu = context.getBean(Menu.class);
        menu.startUI();
    }
}
