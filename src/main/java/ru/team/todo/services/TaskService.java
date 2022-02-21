package ru.team.todo.services;

import ru.team.todo.objects.Task;
import ru.team.todo.objects.User;
import ru.team.todo.ui.ConsoleSession;

import java.util.Collection;
import java.util.Collections;

public class TaskService {

    private final ConsoleSession consoleSession;

    public TaskService(ConsoleSession consoleSession) {
        this.consoleSession = consoleSession;
    }

    //TODO Добавить валидацию и response
    public void addTask(String name, String description) {
        User user = this.consoleSession.getSwitchedUser();
        if (user == null) {
            return;
        }

        user.addTask(name, description);
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
