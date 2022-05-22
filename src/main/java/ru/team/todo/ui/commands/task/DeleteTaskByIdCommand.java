package ru.team.todo.ui.commands.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team.todo.dto.tasks.DeleteTaskRequest;
import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

@Component
public class DeleteTaskByIdCommand extends Command {

    @Autowired
    private TaskService service;

    public DeleteTaskByIdCommand() {
        super("task delete id", "Delete task by id");
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter task id: ");
        int id = scanner.nextInt();
        var request = new DeleteTaskRequest(id);
        var response = service.deleteTask(request);
        System.out.println("Received response: " + response);
        if(!response.getErrors().isEmpty()){
            return;
        }

        System.out.println("Task with id '" + id + "' deleted");
    }
}
