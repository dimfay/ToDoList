package ru.team.todo.repository;

import ru.team.todo.domain.User;

import java.util.List;

public interface UserRepository {

    void addUser(User user);

    void removeUser(User user);

    User getUserByName(String name);

    List<User> getAllUsers();
}
