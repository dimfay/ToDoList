package ru.team.todo.ui.commands.task;

import ru.team.todo.dto.tasks.LinkTaskRequest;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.injections.DIDependency;
import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

@DIComponent
public class LinkTaskCommand extends Command {

    @DIDependency
    private TaskService service;

    public LinkTaskCommand() {
        super("task link", "Add subtasks to the task");
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter first task name: ");
        String firstTaskName = scanner.nextLine();

        System.out.println("Please enter second task name to link: ");
        String secondTaskName = scanner.nextLine();

        var request = new LinkTaskRequest(firstTaskName, secondTaskName);
        var response = service.linkTask(request);

        System.out.println("Received response: " + response);
        if (!response.getErrors().isEmpty()){
            return;
        }

        System.out.println("Task '" + firstTaskName + "' successfully linked with task '" + secondTaskName + "'");
    }
}
