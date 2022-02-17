package ru.team.todo.objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class User {

    private final String name;
    private final Map<Integer, Task> tasksId = new HashMap<>();
    private final Map<String, Task> tasksName = new HashMap<>();
    private int nextTaskId = 1;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTask(String name, String desc) {
        Task task = new Task(this.nextTaskId, name, desc);
        this.tasksId.put(this.nextTaskId, task);
        this.tasksName.put(name, task);
        this.nextTaskId++;
    }

    public void deleteTaskById(int id) {
        Task tmp = this.tasksId.get(id);
        if (tmp == null) {
            return;
        }

        deleteTask(id, tmp.getName());
    }

    public void deleteTaskByName(String name) {
        Task tmp = this.tasksName.get(name);
        if (tmp == null) {
            return;
        }

        deleteTask(tmp.getId(), name);
    }

    private void deleteTask(int id, String name) {
        this.tasksId.remove(id);
        this.tasksName.remove(name);
    }

    public Collection<Task> getAllTasks() {
        return this.tasksId.values();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tasks=" + tasksId +
                '}';
    }
}
