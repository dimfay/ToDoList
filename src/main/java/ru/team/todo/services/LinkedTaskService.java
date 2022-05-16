package ru.team.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.todo.domain.LinkedTask;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.linkedtasks.LinkTaskRequest;
import ru.team.todo.dto.linkedtasks.LinkTaskResponse;
import ru.team.todo.dto.linkedtasks.UnlinkTaskRequest;
import ru.team.todo.dto.linkedtasks.UnlinkTaskResponse;
import ru.team.todo.repository.LinkedTasksRepository;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.CoreError;

import java.util.List;

@Service
public class LinkedTaskService {

    @Autowired
    private LinkedTasksRepository linkedTaskRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ConsoleSession consoleSession;

    public LinkTaskResponse linkTask(LinkTaskRequest request) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new LinkTaskResponse(List.of(new CoreError("User is not switched")));
        }

        Task firstTask = this.taskRepository.findByName(request.getFirstTask());
        if (firstTask == null) {
            return new LinkTaskResponse(List.of(new CoreError("First Task with name '" + request.getFirstTask() + "' not found!")));
        }

        Task secondTask = this.taskRepository.findByName(request.getSecondTask());
        if (secondTask == null) {
            return new LinkTaskResponse(List.of(new CoreError("Second Task with name '" + request.getFirstTask() + "' not found!")));
        }

        this.linkedTaskRepository.save(new LinkedTask(firstTask, secondTask));

        return new LinkTaskResponse(List.of());
    }

    public UnlinkTaskResponse unlinkTask(UnlinkTaskRequest request) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new UnlinkTaskResponse(List.of(new CoreError("User is not switched")));
        }

        Task firstTask = this.taskRepository.findByName(request.getFirstTask());
        if (firstTask == null) {
            return new UnlinkTaskResponse(List.of(new CoreError("First Task with name '" + request.getFirstTask() + "' not found!")));
        }

        Task secondTask = this.taskRepository.findByName(request.getSecondTask());
        if (secondTask == null) {
            return new UnlinkTaskResponse(List.of(new CoreError("Second Task with name '" + request.getFirstTask() + "' not found!")));
        }

        //LinkedTask linkedTask = this.linkedTaskRepository.findLinkedTaskByTasksId(firstTask.getId(), secondTask.getId());
        //if (linkedTask == null) {
        //    return new UnlinkTaskResponse(List.of(new CoreError("Task '" + request.getFirstTask() + "' and Task '" + request.getSecondTask() + "' is not linked!")));
        //}

        //this.linkedTaskRepository.delete(linkedTask);

        return new UnlinkTaskResponse(List.of());
    }

}
