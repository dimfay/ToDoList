package ru.team.todo.ui.commands.task;

import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class DeleteTaskByNameCommand extends Command {

    private final TaskService service;

    public DeleteTaskByNameCommand(TaskService service) {
        super("task delete name", "Delete task by name");
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter task name: ");
        String name = scanner.nextLine();

        this.service.removeTaskByName(name);
        System.out.println("Task with name '" + name + "' deleted");
    }
}
