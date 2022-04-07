package ru.team.todo.repository;

import ru.team.todo.domain.Task;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task);

    void removeTask(Task task);

    Task getTaskById(int id);

    Task getTaskByName(String name);

    List<Task> getAllTasks();
}
