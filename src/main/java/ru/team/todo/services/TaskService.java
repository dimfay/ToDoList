package ru.team.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.*;
import ru.team.todo.dto.users.UserDTO;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.validation.CoreError;
import ru.team.todo.validation.requests.task.AddTaskRequestValidation;
import ru.team.todo.validation.requests.task.DeleteTaskRequestValidation;
import ru.team.todo.validation.requests.task.FindTaskRequestValidation;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddTaskRequestValidation addTaskValidationService;
    @Autowired
    private DeleteTaskRequestValidation deleteTaskRequestValidation;
    @Autowired
    private FindTaskRequestValidation findTaskByNameRequestValidation;

    public AddTaskResponse addTask(AddTaskRequest request) {
        User user = this.userRepository.findByName(request.getUserName());
        if (user == null) {
            return new AddTaskResponse(List.of(new CoreError("Requested user " + request.getUserName() + " not found!")));
        }

        this.taskRepository.save(new Task(user, request.getTaskName(), request.getTaskDescription()));
        return new AddTaskResponse(List.of());
    }

    public DeleteTaskResponse deleteTask(DeleteTaskRequest request) {
        var validationResult = deleteTaskRequestValidation.validate(request);
        if (!validationResult.isEmpty()) {
            return new DeleteTaskResponse(validationResult);
        }

        Optional<Task> value = this.taskRepository.findById(request.getId());
        if (value.isEmpty()) {
            return new DeleteTaskResponse(List.of(new CoreError("Task with id '" + request.getId() + "' not found!")));
        }

        this.taskRepository.delete(value.get());
        return new DeleteTaskResponse(List.of());

    }

    public FindTasksResponse findTasks(FindTasksRequest request) {
        /*var validationResult = findTaskByNameRequestValidation.validate(request);
        if (!validationResult.isEmpty()) {
            return new FindTasksResponse(validationResult, List.of());
        }*/

        /*User user = this.userRepository.findByName(request.getUserName());
        if (user == null) {
            return new FindTasksResponse(List.of(new CoreError("Requested user " + request.getUserName() + " not found!")), List.of());
        }*/
        var tasks = taskRepository.findAllByUserName(request.getUserName());
        return new FindTasksResponse(List.of(), tasks);
    }

    public List<TaskDTO> findAllTasks(){
        List<Task> tmp = taskRepository.findAll();
        return tmp.stream()
                .map(this::convert)
                .toList();
    }

    private TaskDTO convert(Task task){
        var userDTO = new UserDTO(task.getUser().getId(), task.getUser().getName());
        return new TaskDTO(task.getId(), userDTO, task.getName(), task.getDescription());
    }
}
