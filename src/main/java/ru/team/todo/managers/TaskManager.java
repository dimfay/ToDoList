package ru.team.todo.managers;

import ru.team.todo.objects.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TaskManager {

    private List<Task> tasks = new ArrayList<>();

    private int idSequence = 1;

    public void addTask(String name, String desc) {
        Task task = new Task(this.idSequence, name, desc);
        tasks.add(task);
        this.idSequence++;
    }

    public void deleteTaskById(int id) {
    	id -= 1;
        if (isValidId(id)) {
        	tasks.remove(id);
        }
    }

    public void deleteTaskByName(String name) {
        for (int i = 0; i < tasks.size(); i++) {
	        if (tasks.get(i).getName().equals(name)) {
	        	tasks.remove(i);
	        }
        }
    }
 
    public List<Task> getAllTasks() {
        return tasks;
    }
    
    private boolean isValidId(int id) {
    	if (id >= 0 && id < tasks.size()) {
    		return true;
    	}
    	return false;
    }
}
