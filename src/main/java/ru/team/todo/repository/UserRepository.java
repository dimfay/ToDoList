package ru.team.todo.repository;

import ru.team.todo.domain.User;

import java.util.Collection;

public interface UserRepository {

    User addUser(User user);

    void removeUser(String name);

    User getUserByName(String name);

    Collection<User> getAllUsers();
}
