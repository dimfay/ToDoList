package ru.team.todo.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Task {

    private int id;
    private String name;
    private String description;
    private final Set<Task> linkedTasks = new HashSet<>();

    public Task () {

    }

    public Task(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.description = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayInfo() {
        return "[ID: " + this.id + ". Name: " + this.name + ". Description: '" + this.description + "']";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void linkTask(Task task) {
        this.linkedTasks.add(task);
    }

    public void unlinkTask(Task task) {
        this.linkedTasks.remove(task);
    }

    public Set<Task> getAllLinkedTasks() {
        return this.linkedTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + description + '\'' +
                '}';
    }
}
