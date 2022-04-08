package ru.team.todo.ui.commands.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.team.todo.domain.LinkedTask;
import ru.team.todo.domain.Task;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.services.TaskService;
import ru.team.todo.ui.commands.Command;

import java.util.List;

@Component
public class FindTasksCommand extends Command {

    @Autowired
    private TaskService service;

    public FindTasksCommand() {
        super("task find", "Display all tasks");
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

            for (LinkedTask linkedTask : task.getLinkedTasks()) {
                System.out.println(" - " + linkedTask.getLinkedTask().getDisplayInfo());
            }
        }
    }
}
