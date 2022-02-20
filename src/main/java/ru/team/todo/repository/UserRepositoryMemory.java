package ru.team.todo.repository;

import ru.team.todo.dto.AddUserRequest;
import ru.team.todo.dto.AddUserResponse;
import ru.team.todo.objects.User;
import ru.team.todo.validation.ValidationService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dto.AddToDoResponse;

public class UserRepositoryMemory implements UserRepository {

    private final Map<String, User> users = new HashMap<>();
     

    @Override
    public User addUser(User user) {
        users.put(user.getName(), user);
        return user;
    }
    
    private User convertUser(AddUserRequest addUserRequest) {
    	return new User(addUserRequest.getName());
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