package ru.team.todo.ui.commands.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team.todo.dto.linkedtasks.UnlinkTaskRequest;
import ru.team.todo.services.LinkedTaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

@Component
public class UnlinkTaskCommand extends Command {

    @Autowired
    private LinkedTaskService service;

    public UnlinkTaskCommand() {
        super("task unlink", "Delete subtasks from the task");
    }

    @Override
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter user name: ");
        String userName = scanner.nextLine();
        System.out.println("Please enter first task name: ");
        String firstTaskName = scanner.nextLine();

        System.out.println("Please enter second task name to link: ");
        String secondTaskName = scanner.nextLine();

       var request = new UnlinkTaskRequest(userName, firstTaskName, secondTaskName);
       var response = service.unlinkTask(request);

        System.out.println("Received response: " + response);
        if (!response.getErrors().isEmpty()){
            return;
        }

        System.out.println("Task '" + firstTaskName + "' successfully unlinked from task '" + secondTaskName + "'");
    }
}
