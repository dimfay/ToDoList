package ru.team.todo.services;

import ru.team.todo.dto.AddUserResponse;
import ru.team.todo.objects.Task;
import ru.team.todo.objects.User;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.CoreError;
import ru.team.todo.validation.ValidationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaskService {

    private final ConsoleSession consoleSession;

    public TaskService(ConsoleSession consoleSession) {
        this.consoleSession = consoleSession;
    }

    //TODO Добавить валидацию и response
    public AddTaskResponse addTask(AddTaskRequest request) {
        List<CoreError> errors = new ArrayList<>();
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            errors.add(new CoreError("Пользователь не был выбран!"));
            return new AddTaskResponse(errors);
        }


        try {
            user.addTask(request.getTaskName(), request.getTaskDescription());
        }
        catch (ValidationException e) {
            errors.add(new CoreError(e.getMessage()));
        }

        return new AddTaskResponse(errors);
    }

    //Допустим сделаем просто статический класс здесь для теста
    public static class AddTaskRequest {
        private final String taskName;
        private final String taskDescription;

        public AddTaskRequest(String taskName, String taskDescription) {
            this.taskName = taskName;
            this.taskDescription = taskDescription;
        }

        public String getTaskName() {
            return taskName;
        }

        public String getTaskDescription() {
            return taskDescription;
        }
    }

    //Здесь допустим класс респонса
    public static class AddTaskResponse {
        private final List<CoreError> errors;

        public AddTaskResponse(List<CoreError> errors) {
            this.errors = errors;
        }

        public List<CoreError> getErrors() {
            return errors;
        }

    }

    //TODO Добавить валидацию и response
    public void removeTaskByName(String name) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return;
        }

        user.deleteTaskByName(name);
    }

    //TODO Добавить валидацию и response
    public void removeTaskById(int id) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return;
        }

        user.deleteTaskById(id);
    }

    //TODO Добавить валидацию и response
    public Collection<Task> getAllTasks() {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return Collections.emptyList();
        }

        return user.getAllTasks();
    }

    //TODO Добавить валидацию и response
    public void linkTask(String firstTaskName, String secondTaskName) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return;
        }

        Task firstTask = user.getTaskByName(firstTaskName);
        if (firstTask == null) {
            return;
        }

        Task secondTask = user.getTaskByName(secondTaskName);
        if (secondTask == null) {
            return;
        }

        firstTask.linkTask(secondTask);
    }

    //TODO Добавить валидацию и response
    public void unlinkTask(String firstTaskName, String secondTaskName) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return;
        }

        Task firstTask = user.getTaskByName(firstTaskName);
        if (firstTask == null) {
            return;
        }

        Task secondTask = user.getTaskByName(secondTaskName);
        if (secondTask == null) {
            return;
        }

        firstTask.unlinkTask(secondTask);
    }
}
