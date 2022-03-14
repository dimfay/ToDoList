package ru.team.todo.ui.commands.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

@Component
public class AddTaskCommand extends Command {

    @Autowired
    private TaskService service;

    public AddTaskCommand() {
        super("task add", "Add new task");
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter task name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter task description: ");
        String description = scanner.nextLine();

        var request = new AddTaskRequest(name, description);
        var response = service.addTask(request);
        System.out.println("Received response: " + response );

        if (!response.getErrors().isEmpty()) {
            return;
        }
        System.out.println("Task '" + name + "' added");
    }
}
