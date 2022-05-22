package ru.team.todo.ui.commands.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team.todo.dto.linkedtasks.LinkTaskRequest;
import ru.team.todo.services.LinkedTaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

@Component
public class LinkTaskCommand extends Command {

    @Autowired
    private LinkedTaskService service;

    public LinkTaskCommand() {
        super("task link", "Add subtasks to the task");
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter user name: ");
        String userName = scanner.nextLine();
        System.out.println("Please enter first task name: ");
        String firstTaskName = scanner.nextLine();

        System.out.println("Please enter second task name to link: ");
        String secondTaskName = scanner.nextLine();

        var request = new LinkTaskRequest(userName, firstTaskName, secondTaskName);
        var response = service.linkTask(request);

        System.out.println("Received response: " + response);
        if (!response.getErrors().isEmpty()){
            return;
        }

        System.out.println("Task '" + firstTaskName + "' successfully linked with task '" + secondTaskName + "'");
    }
}
