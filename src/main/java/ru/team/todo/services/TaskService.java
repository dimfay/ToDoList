package ru.team.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.AddTaskResponse;
import ru.team.todo.dto.tasks.DeleteTaskByIdRequest;
import ru.team.todo.dto.tasks.DeleteTaskByIdResponse;
import ru.team.todo.dto.tasks.DeleteTaskByNameRequest;
import ru.team.todo.dto.tasks.DeleteTaskByNameResponse;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.dto.users.FindUserResponse;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.CoreError;
import ru.team.todo.validation.requests.task.AddTaskRequestValidation;
import ru.team.todo.validation.requests.task.DeleteTaskByIdRequestValidation;
import ru.team.todo.validation.requests.task.DeleteTaskByNameRequestValidation;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;
    @Autowired
    private ConsoleSession consoleSession;
    @Autowired
    private AddTaskRequestValidation addTaskValidationService;
    @Autowired
    private DeleteTaskByIdRequestValidation deleteTaskByIdValidationService;
    @Autowired
    private DeleteTaskByNameRequestValidation deleteTaskByNameValidationService;
    @Autowired
    private UserService userService;

    public AddTaskResponse addTask(AddTaskRequest request) {
        var validationResult = addTaskValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new AddTaskResponse(validationResult);
        }
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new AddTaskResponse(List.of(new CoreError("The user is not switched")));
        }

        this.repository.save(new Task(user, request.getName(), request.getDescription()));
        return new AddTaskResponse(List.of());
    }

    public AddTaskResponse addTask(AddTaskRequest request, String username){
        var validationResult = addTaskValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new AddTaskResponse(validationResult);
        }
        var userResponse = userService.findUsers(new FindUserRequest(List.of(username)));
        var user = userResponse.getUsers().get(0);
        if (user == null) {
            return new AddTaskResponse(List.of(new CoreError("The user is not switched")));
        }

        this.repository.save(new Task(user, request.getName(), request.getDescription()));
        return new AddTaskResponse(List.of());
    }

    public DeleteTaskByNameResponse deleteTaskByName(DeleteTaskByNameRequest request) {
        var validationResult = deleteTaskByNameValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new DeleteTaskByNameResponse(validationResult);
        }
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new DeleteTaskByNameResponse(List.of(new CoreError("The user is not switched")));
        }

        Task task = this.repository.findByName(request.getName());
        if (task == null) {
            return new DeleteTaskByNameResponse(List.of(new CoreError("Task with name '" + request.getName() + "' not found!")));
        }

        this.repository.delete(task);
        return new DeleteTaskByNameResponse(List.of());

    }

    public DeleteTaskByIdResponse deleteTaskById(DeleteTaskByIdRequest request) {
        var validationResult = deleteTaskByIdValidationService.validate(request);
        if (!validationResult.isEmpty()) {
            return new DeleteTaskByIdResponse(validationResult);
        }
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new DeleteTaskByIdResponse(List.of(new CoreError("The user is not switched")));
        }

        Optional<Task> task = this.repository.findById(request.getId());
        if (task.isEmpty()) {
            return new DeleteTaskByIdResponse(List.of(new CoreError("Task with id '" + request.getId() + "' not found!")));
        }

        this.repository.delete(task.get());
        return new DeleteTaskByIdResponse(List.of());
    }

    //TODO Реквесты пока что нигде не используются
    public FindTasksResponse findAllTasks(FindTasksRequest request) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return new FindTasksResponse(List.of(new CoreError("The user is not switched")), List.of());
        }

        return new FindTasksResponse(List.of(), user.getTasks());
    }

    public FindTasksResponse findUserTasks(FindTasksRequest request, User user){
        if (user == null) {
            return new FindTasksResponse(List.of(new CoreError("The user is not switched")), List.of());
        }

        return new FindTasksResponse(List.of(), user.getTasks());
    }
}
