package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.AddTaskResponse;
import ru.team.todo.dto.tasks.DeleteTaskRequest;
import ru.team.todo.dto.tasks.DeleteTaskResponse;
import ru.team.todo.dto.tasks.EditTaskRequest;
import ru.team.todo.dto.tasks.EditTaskResponse;
import ru.team.todo.services.TaskService;

@RequestMapping("/action/task")
@RestController()
@AllArgsConstructor
public class TasksActionRestController {
    private final TaskService taskService;

    @PostMapping("/add")
    public AddTaskResponse addTask(@RequestBody AddTaskRequest request) {
        return taskService.addTask(request);
    }

    @PostMapping("/delete")
    public DeleteTaskResponse deleteTask(@RequestBody DeleteTaskRequest request) {
        return taskService.deleteTask(request);
    }

    @PostMapping("/edit")
    public EditTaskResponse editTask(@RequestBody EditTaskRequest request) {
        return taskService.editTask(request);
    }

}
