package ru.team.todo.services;

import ru.team.todo.objects.User;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.ui.ConsoleSession;

public class UserService {

    private final UserRepository repository;
    private final ConsoleSession consoleSession;

    public UserService(UserRepository repository, ConsoleSession consoleSession) {
        this.repository = repository;
        this.consoleSession = consoleSession;
    }

    //TODO Добавить валидацию и response
    public void addUser(String name) {
        this.repository.addUser(name);
    }

    //TODO Добавить валидацию и response
    public void removeUser(String name) {
        this.repository.removeUser(name);
    }

    //TODO Добавить валидацию и response
    public void switchUser(String name) {
        User user = this.repository.getUserByName(name);
        if (user == null) {
            return;
        }
        this.consoleSession.setSwitchedUser(user);
    }

}
