package ru.team.todo.domain;

import lombok.Data;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class User {

    @NonNull
    private final String name;
    private final Map<Integer, Task> tasksId = new HashMap<>();
    private final Map<String, Task> tasksName = new HashMap<>();
    private int nextTaskId = 1;


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


    public Task getTaskByName(String name) {
        return this.tasksName.get(name);
    }

    public Collection<Task> getAllTasks() {
        return this.tasksId.values();
    }

}
