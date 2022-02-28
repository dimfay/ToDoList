package ru.team.todo.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public Task getTaskByName(String name) {
        return this.tasksName.get(name);
    }

    public Task getTaskById(int id) {
        return this.tasksId.get(id);
    }

    public Collection<Task> getAllTasks() {
        return this.tasksId.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return nextTaskId == user.nextTaskId && Objects.equals(name, user.name) && Objects.equals(tasksId, user.tasksId) && Objects.equals(tasksName, user.tasksName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tasksId, tasksName, nextTaskId);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tasks=" + tasksId +
                '}';
    }
}
