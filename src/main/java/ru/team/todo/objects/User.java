package ru.team.todo.objects;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name = "";
    private final List<Task> tasks = new ArrayList<>();
    private int idSequence = 1;
    
    public User (String name) {
    	this.name = name;
    }
    
    public String getName() {
		return name;
	}
    
    public List<Task> getTasks() {
		return tasks;
	}
    
    public int getIdSequence() {
		return idSequence;
	}
    
    public void increaseIdSequence() {
    	idSequence++;
    }

	@Override
	public String toString() {
		return "User [name=" + name + ", tasks=" + tasks + ", idSequence=" + idSequence + "]";
	}
    
    
}
