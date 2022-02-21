package ru.team.todo.ui.commands.task;

import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Scanner;

public class LinkTaskCommand extends Command {

    private final TaskService service;

    public LinkTaskCommand(TaskService service) {
        super("task add link", "Add subtasks to the task");
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter first task name: ");
        String firstTaskName = scanner.nextLine();

        System.out.println("Please enter second task name to link: ");
        String secondTaskName = scanner.nextLine();

        this.service.linkTask(firstTaskName, secondTaskName);
        System.out.println("Task '" + firstTaskName + "' successfully linked with task '" + secondTaskName + "'");
    }
}
