package ru.team.todo.domain;

import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class Task {

    private final int id;
    @NonNull
    private final String name;
    @NonNull
    private final String description;
    private final Set<Task> linkedTasks = new HashSet<>();

    public String getDisplayInfo() {
        return "[ID: " + this.id + ". Name: " + this.name + ". Description: '" + this.description + "']";
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
}
