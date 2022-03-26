package ru.team.todo.repository;

import ru.team.todo.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class UserRepositoryMemory implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    public UserRepositoryMemory() {
        //По умолчанию имеем пользователя admin
        addUser("admin");
    }

    @Override
    public void addUser(String name) {
        this.users.put(name, new User(name));
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