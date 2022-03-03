package ru.team.todo.ui.commands.task;

import ru.team.todo.domain.Task;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.Collection;
import java.util.List;

public class FindTasksCommand extends Command {

    private final TaskService service;

    public FindTasksCommand(TaskService service) {
        super("task find", "Display all tasks");
        this.service = service;
    }

    @Override
    public void execute() {

        var request = new FindTasksRequest(List.of());
        var response = service.findAllTasks(request);

        if (response.getTasks().isEmpty()) {
            System.out.println("Tasks not found!");
            return;
        }
        System.out.println("Received response: " + response);
        if (!response.getErrors().isEmpty()){
            return;
        }
        var tasks = response.getTasks();

        for (Task task : tasks) {
            System.out.println(task.getDisplayInfo());
            for (Task linked : task.getAllLinkedTasks()) {
                System.out.println(" - " + linked.getDisplayInfo());
            }
        }
    }
}
