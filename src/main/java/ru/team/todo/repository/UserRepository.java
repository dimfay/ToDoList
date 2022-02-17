package ru.team.todo.repository;

import ru.team.todo.objects.User;

import java.util.Collection;

public interface UserRepository {

    void addUser(String name);

    void removeUser(String name);

    User getUserByName(String name);

    Collection<User> getAllUsers();
}
