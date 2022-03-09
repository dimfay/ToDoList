package ru.team.todo.ui.commands.task;

import ru.team.todo.dto.tasks.DeleteTaskByNameRequest;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.injections.DIDependency;
import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

@DIComponent
public class DeleteTaskByNameCommand extends Command {

    @DIDependency
    private TaskService service;

    public DeleteTaskByNameCommand() {
        super("task delete name", "Delete task by name");
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
