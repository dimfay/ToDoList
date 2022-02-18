package ru.team.todo.services;

import ru.team.todo.objects.User;
import ru.team.todo.ui.ConsoleSession;

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
}
