package ru.team.todo.managers;

import ru.team.todo.objects.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<String, User> users = new HashMap<>();

    private User currentUser = null;

    public void addUser(String name) {
        User user = new User(name);
        users.put(name, user);
        System.out.println("Added user " + name);
    }

    public void deleteUser(String name) {
        this.users.remove(name);
        this.currentUser = null;
        System.out.println("User " + name + " has been deleted");
    }

    public User getUserByName(String name) {
        return this.users.get(name);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * @return Возвращает список всех пользователей
     */
    public Collection<User> getAllUsers() {
        return users.values();
    }

}