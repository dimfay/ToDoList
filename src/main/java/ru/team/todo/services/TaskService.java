package ru.team.todo.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.*;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.validation.CoreError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public AddTaskResponse addTask(AddTaskRequest request) {
        User user = this.userRepository.findByName(request.getUserName());
        if (user == null) {
            return new AddTaskResponse(List.of(new CoreError("Requested user " + request.getUserName() + " not found!")));
        }

        this.taskRepository.save(new Task(user, request.getTaskName(), request.getTaskDescription()));
        return new AddTaskResponse(List.of());
    }

    public DeleteTaskResponse deleteTask(DeleteTaskRequest request) {
        Optional<Task> value = this.taskRepository.findById(request.getId());
        if (value.isEmpty()) {
            return new DeleteTaskResponse(List.of(new CoreError("Task with id '" + request.getId() + "' not found!")));
        }

        this.taskRepository.delete(value.get());
        return new DeleteTaskResponse(List.of());
    }

    public EditTaskResponse editTask(EditTaskRequest request) {
        Optional<Task> value = this.taskRepository.findById(request.getId());
        if (value.isEmpty()) {
            return new EditTaskResponse(List.of(new CoreError("Task with id '" + request.getId() + "' not found!")));
        }

        Task task = value.get();
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        this.taskRepository.save(task);

        return new EditTaskResponse(List.of());
    }

    public FindTasksResponse findTask(FindTasksRequest request) {
        String userRequested = request.getUserName();
        if (userRequested != null) {
            User user = this.userRepository.findByName(userRequested);
            if (user == null) {
                return new FindTasksResponse(List.of(new CoreError("Requested user " + userRequested + " not found!")), List.of());
            }
            return new FindTasksResponse(List.of(), convertAllTasks(user.getTasks()));
        }

        return new FindTasksResponse(List.of(), convertAllTasks(this.taskRepository.findAll()));
    }

    private static List<TaskDTO> convertAllTasks(Collection<Task> tasks) {
        List<TaskDTO> dto = new ArrayList<>();
        for (Task task : tasks) {
            dto.add(convert(task));
        }
        return dto;
    }

    private static TaskDTO convert(Task task) {
        return new TaskDTO(task.getId(), task.getName(), task.getDescription());
    }
}
