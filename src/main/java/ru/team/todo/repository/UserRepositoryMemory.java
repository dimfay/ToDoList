package ru.team.todo.repository;

import ru.team.todo.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryMemory implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    public UserRepositoryMemory() {
        //По умолчанию имеем пользователя admin
        addUser(new User("admin"));
    }

    @Override
    public User addUser(User user) {
        users.put(user.getName(), user);
        return user;
    }

    @Override
    public void removeUser(String name) {
        this.users.remove(name);
    }

    @Override
    public User getUserByName(String name) {
        return this.users.get(name);
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }

}