package ru.team.todo.ui.commands.task;

import ru.team.todo.dto.tasks.DeleteTaskByNameRequest;
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
        var request = new DeleteTaskByNameRequest(name);
        var response = service.deleteTaskByName(request);
        System.out.println("Received response: " + response);
        if (!response.getErrors().isEmpty()){
            return;
        }
        System.out.println("Task with name '" + name + "' deleted");
    }
}
