package ru.team.todo.objects;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private final int id;
    private final String name;
    private final String description;
    private final List<Integer> subtaskIds = new ArrayList<>();

    public Task(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.description = desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
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
