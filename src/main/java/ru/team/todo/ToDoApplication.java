package ru.team.todo;

import ru.team.todo.managers.TaskManager;
import ru.team.todo.ui.commands.AddCommand;
import ru.team.todo.ui.commands.DeleteCommand;
import ru.team.todo.ui.commands.FindTasksCommand;
import ru.team.todo.ui.Menu;

public class ToDoApplication {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        new Menu()
                .addCommand(new AddCommand(manager))
                .addCommand(new FindTasksCommand(manager))
                .addCommand(new DeleteCommand(manager))
                .startUI();
    }
}
