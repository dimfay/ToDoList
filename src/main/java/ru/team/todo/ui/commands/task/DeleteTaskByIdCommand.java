package ru.team.todo.ui.commands.task;

import ru.team.todo.dto.tasks.DeleteTaskByIdRequest;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.injections.DIDependency;
import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

@DIComponent
public class DeleteTaskByIdCommand extends Command {

    @DIDependency
    private TaskService service;

    public DeleteTaskByIdCommand() {
        super("task delete id", "Delete task by id");
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter task id: ");
        int id = scanner.nextInt();
        var request = new DeleteTaskByIdRequest(id);
        var response = service.deleteTaskById(request);
        System.out.println("Received response: " + response);
        if(!response.getErrors().isEmpty()){
            return;
        }

        System.out.println("Task with id '" + id + "' deleted");
    }
}
