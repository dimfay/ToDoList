package ru.team.todo.service;

import ru.team.todo.objects.Task;
import ru.team.todo.objects.User;

import java.util.*;

public class UserService {

    private final Map<Integer, User> users = new HashMap<>();

    private int count = 1;

    public User addUser(String name) {
        User user = new User(name);
        this.users.put(this.count, user);
        System.out.println(String.format("Added user %s with id %d", name, count));
        this.count++;
        return user;
    }

    public boolean deleteUserById(int id) {
        User removedUser = users.remove(id);
        return removedUser != null;
    }

    public void deleteUserByName(String name) {
        System.out.println(String.format("Trying to remove user with name %s", name));
        users.entrySet().stream()
                .filter(it -> it.getValue().getName().equals(name))
                .findFirst()
                .ifPresent(user -> {
                    users.remove(user.getKey());
                    System.out.println(String.format("User {%s} has been deleted", user));
                });
    }

    public Optional<User> findUserByName(String name) {
        System.out.println(String.format("Searching user by name: %s", name));
        Optional<User> user = users.values().stream()
                .filter(it -> it.getName().equals(name))
                .findFirst();
        System.out.println(String.format("Found user: {%s}", user));

        return user;
    }

    public void addTaskFoUser(User user, Task task) {
        if (user.getTasks().contains(task)) {
            System.out.println(String.format("User contain task {%s}", task));
            return;
        }
        user.getTasks().add(task);
        System.out.println(String.format("Added task %s for user %s", task, user));
    }

    /**
     * @return Возвращает список всех пользователей
     */
    public Collection<User> getAllUsers() {
        return this.users.values();
    }

}
