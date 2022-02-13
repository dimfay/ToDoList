package ru.team.todo.service;

import ru.team.todo.objects.Task;
import ru.team.todo.objects.User;

import java.util.*;

public final class TaskService {

    private final Map<Integer, Task> tasks = new HashMap<>();

    private int count = 1;

    public Task addTask(String name, String desc) {
        Task task = new Task(this.count, name, desc);
        this.tasks.put(this.count, task);

        this.count++;
        return task;
    }

    public boolean deleteTaskById(int id) {
        Task removedTask = tasks.remove(id);
        return removedTask != null;
    }

    public void deleteTaskByName(String name) {
        System.out.println(String.format("Trying to remove task with name %s", name));
        tasks.entrySet().stream()
                .filter(it -> it.getValue().getName().equals(name))
                .findFirst()
                .ifPresent(task -> {
                    tasks.remove(task.getValue().getId());
                    System.out.println(String.format("Task {%s} has been deleted", task));
                });

    }

    public Optional<Task> findTaskByName(String name) {
        System.out.println(String.format("Searching task by name: %s", name));
        Optional<Task> task = tasks.values().stream()
                .filter(it -> it.getName().equals(name))
                .findFirst();
        System.out.println(String.format("Found task: {%s}", task));
        return task;
    }

    /**
     * @return Возвращает список всех задач
     */
    public Collection<Task> getAllTasks() {
        return this.tasks.values();
    }
}
