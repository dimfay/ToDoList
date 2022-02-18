package ru.team.todo.ui.commands.task;

import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class DeleteTaskByIdCommand extends Command {

    private final TaskService service;

    public DeleteTaskByIdCommand(TaskService service) {
        super("task delete id", "Delete task by id");
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter task id: ");
        int id = scanner.nextInt();

        this.service.removeTaskById(id);
        System.out.println("Task with id '" + id + "' deleted");
    }
}
