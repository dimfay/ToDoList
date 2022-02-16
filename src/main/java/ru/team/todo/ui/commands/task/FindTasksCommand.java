package ru.team.todo.ui.commands.task;

import ru.team.todo.managers.UserService;
import ru.team.todo.objects.Task;
import ru.team.todo.objects.User;
import ru.team.todo.ui.commands.Command;

import java.util.Collection;

public class FindTasksCommand extends Command {

    private final UserService manager;

    public FindTasksCommand(UserService manager) {
        super("task find", "Display all tasks");
        this.manager = manager;
    }

    @Override
    public void execute() {
        User user = manager.getCurrentUser();
        if (user == null) {
            System.out.println("No user selected.");
            return;
        }

        Collection<Task> tasks = user.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Tasks not found!");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }
}
