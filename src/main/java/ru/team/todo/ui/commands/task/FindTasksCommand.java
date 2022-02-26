package ru.team.todo.ui.commands.task;

import ru.team.todo.domain.Task;
import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Collection;

public class FindTasksCommand extends Command {

    private final TaskService service;

    public FindTasksCommand(TaskService service) {
        super("task find", "Display all tasks");
        this.service = service;
    }

    @Override
    public void execute() {
        Collection<Task> tasks = this.service.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Tasks not found!");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task.getDisplayInfo());
            for (Task linked : task.getAllLinkedTasks()) {
                System.out.println(" - " + linked.getDisplayInfo());
            }
        }
    }
}
