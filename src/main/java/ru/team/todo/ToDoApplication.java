package ru.team.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.team.todo.ui.Menu;

@SpringBootApplication
public class ToDoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ToDoApplication.class);

        var menu = context.getBean(Menu.class);
        menu.startUI();
    }
}
