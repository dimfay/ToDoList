package ru.team.todo.managers;

import ru.team.todo.objects.Task;
import ru.team.todo.objects.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class TaskService {

	User user;
	UserService userService;
	private List<Task> tasks;
    //private int idSequence = 1;

    public TaskService(UserService userService) {
    	this.userService = userService;
	}
    
    
    public void setUser() {
    	Optional<User> optional = userService.getCurrentUser();
    	if (optional.isPresent()) {
    		user = optional.get();
    		tasks = user.getTasks();
    	}
    }
    
    public void addTask(String name, String desc) {
        Task task = new Task(user.getIdSequence(), name, desc);
        tasks.add(task);
        user.increaseIdSequence();
    }

    public void deleteTaskById(int id) {
    	id -= 1;
        if (isValidId(id)) {
        	tasks.remove(id);
        }
    }

    public void deleteTaskByName(String name) {
        for (int i = 0; i < user.getTasks().size(); i++) {
	        if (tasks.get(i).getName().equals(name)) {
	        	tasks.remove(i);
	        }
        }
    }

	public List<Task> getAllTasks() { return tasks; }
	 
	public UserService getUserService() {
		return userService;
	}
    
    private boolean isValidId(int id) {
    	if (id >= 0 && id < tasks.size()) {
    		return true;
    	}
    	return false;
    }
}
