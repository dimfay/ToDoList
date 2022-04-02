package ru.team.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.AddTaskResponse;
import ru.team.todo.dto.tasks.DeleteTaskByIdRequest;
import ru.team.todo.dto.tasks.DeleteTaskByIdResponse;
import ru.team.todo.dto.tasks.DeleteTaskByNameRequest;
import ru.team.todo.dto.tasks.DeleteTaskByNameResponse;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.dto.tasks.LinkTaskRequest;
import ru.team.todo.dto.tasks.LinkTaskResponse;
import ru.team.todo.dto.tasks.UnlinkTaskRequest;
import ru.team.todo.dto.tasks.UnlinkTaskResponse;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.CoreError;
import ru.team.todo.validation.requests.task.AddTaskRequestValidation;
import ru.team.todo.validation.requests.task.DeleteTaskByIdRequestValidation;
import ru.team.todo.validation.requests.task.DeleteTaskByNameRequestValidation;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private ConsoleSession consoleSession;
    @Autowired
    private AddTaskRequestValidation addTaskValidationService;
    @Autowired
    private DeleteTaskByIdRequestValidation deleteTaskByIdValidationService;
    @Autowired
    private DeleteTaskByNameRequestValidation deleteTaskByNameValidationService;

    public AddTaskResponse addTask(AddTaskRequest request) {
        var validationResult = addTaskValidationService.validate(request);
        if (!validationResult.isEmpty()){
            return new AddTaskResponse(validationResult);
        }
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new AddTaskResponse(List.of(new CoreError("The user is not switched")));
        }

        //user.addTask(request.getName(), request.getDescription());
        return new AddTaskResponse(List.of());
    }

    public DeleteTaskByNameResponse deleteTaskByName(DeleteTaskByNameRequest request) {
        var validationResult = deleteTaskByNameValidationService.validate(request);
        if (!validationResult.isEmpty()){
            return new DeleteTaskByNameResponse(validationResult);
        }
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new DeleteTaskByNameResponse(List.of(new CoreError("The user is not switched")));
        }
        //user.deleteTaskByName(request.getName());
        return new DeleteTaskByNameResponse(List.of());

    }

    public DeleteTaskByIdResponse deleteTaskById(DeleteTaskByIdRequest request) {
        var validationResult = deleteTaskByIdValidationService.validate(request);
        if (!validationResult.isEmpty()){
            return new DeleteTaskByIdResponse(validationResult);
        }
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new DeleteTaskByIdResponse(List.of(new CoreError("The user is not switched")));
        }

        //user.deleteTaskById(request.getId());
        return new DeleteTaskByIdResponse(List.of());
    }

       public FindTasksResponse findAllTasks(FindTasksRequest request) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new FindTasksResponse(List.of(new CoreError("The user is not switched")), List.of());
        }

        if (request.getTasks().isEmpty()) {
            return new FindTasksResponse(List.of(), new ArrayList<>());
        }

        return new FindTasksResponse(List.of(new CoreError("Something went wrong")), List.of());
    }

    public LinkTaskResponse linkTask(LinkTaskRequest request) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new LinkTaskResponse(List.of(new CoreError("User is not switched")));
        }

        /*Task firstTask = user.getTaskByName(request.getFirstTask());
        if (firstTask == null) {
            return new LinkTaskResponse(List.of(new CoreError("User does not have the first task")));
        }

        Task secondTask = user.getTaskByName(request.getSecondTask());
        if (secondTask == null) {
            return new LinkTaskResponse(List.of(new CoreError("User does not have the second task")));
        }

        firstTask.linkTask(secondTask);*/
        return new LinkTaskResponse(List.of());
    }


    public UnlinkTaskResponse unlinkTask(UnlinkTaskRequest request) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new UnlinkTaskResponse(List.of(new CoreError("User is not switched")));
        }

        /*Task firstTask = user.getTaskByName(request.getFirstName());
        if (firstTask == null) {
            return new UnlinkTaskResponse(List.of(new CoreError("User does not have the first task")));
        }

        Task secondTask = user.getTaskByName(request.getSecondName());
        if (secondTask == null) {
            return new UnlinkTaskResponse(List.of(new CoreError("User does not have the second task")));
        }

        firstTask.unlinkTask(secondTask);*/
        return new UnlinkTaskResponse(List.of());
    }
}
