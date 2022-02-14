package ru.team.todo.managers;

import ru.team.todo.objects.Task;
import ru.team.todo.objects.User;

import java.awt.geom.Area;
import java.net.SecureCacheResponse;
import java.util.*;

public class UserService {

    private final Map<Integer, User> users = new HashMap<>();
    private User currentUser;
    private int count = 1;

    public void addUser(String name) {
        User user = new User(name);
        users.put(this.count, user);
        System.out.println(String.format("Added user %s with id %d", name, count));
        switchUser(name);
        count++;
    }
    
    public void switchUser(String name) {
    	currentUser = getUser(name);
    }
    
    public Optional<User> getCurrentUser() { 
    	return Optional.ofNullable(currentUser);
    }
    
    private User getUser(int id) {
    	return users.get(id);
    }
    
    private User getUser(String name) throws NoSuchElementException {
        Optional<User> user = users.values().stream()
                .filter(it -> name.equals(it.getName()))
                .findFirst();
        if (user.isPresent()) {
        	return user.get();
        } else {
        	throw new NoSuchElementException();
        }
    }

        
    private boolean checkId(int id) {
    	if (id >= 0 && id < users.size()) {
    		return true;
    	} 
    	return false;
    }
    
    public void deleteUser(String name) {
    	users.entrySet().stream()
        	.filter(it -> it.getValue().getName().equals(name))
        	.findFirst()
        	.ifPresent(user -> {
        		users.remove(user.getKey());
        		System.out.println(String.format("User {%s} has been deleted", user));
        	});
    	currentUser = null;
    }
    
    
    public void findAllUsers() {
    	Collection<User> allUsers = getAllUsers();
    	System.out.println("All users:");
    	for (User user : allUsers) {
    		System.out.println(user.getName());
    	}
    }
    
    /**
     * @return Возвращает список всех пользователей
     */
    public Collection<User> getAllUsers() {
        return users.values();
    }

}