package ru.team.todo.ui.commands.task;

import ru.team.todo.dto.tasks.UnlinkTaskRequest;
import ru.team.todo.injections.DIComponent;
import ru.team.todo.injections.DIDependency;
import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

@DIComponent
public class UnlinkTaskCommand extends Command {

    @DIDependency
    private TaskService service;

    public UnlinkTaskCommand() {
        super("task unlink", "Delete subtasks from the task");
    }

    @Override
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter first task name: ");
        String firstTaskName = scanner.nextLine();

        System.out.println("Please enter second task name to link: ");
        String secondTaskName = scanner.nextLine();

       var request = new UnlinkTaskRequest(firstTaskName, secondTaskName);
       var response = service.unlinkTask(request);

        System.out.println("Received response: " + response);
        if (!response.getErrors().isEmpty()){
            return;
        }

        System.out.println("Task '" + firstTaskName + "' successfully unlinked from task '" + secondTaskName + "'");
    }
}