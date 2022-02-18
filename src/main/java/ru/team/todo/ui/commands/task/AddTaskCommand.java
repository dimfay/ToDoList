package ru.team.todo.ui.commands.task;

import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class AddTaskCommand extends Command {

    private final TaskService service;

    public AddTaskCommand(TaskService service) {
        super("task add", "Add new task");
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter task name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter task description: ");
        String description = scanner.nextLine();

        this.service.addTask(name, description);
        System.out.println("Task '" + name + "' added");
    }
}
