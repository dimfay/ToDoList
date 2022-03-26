package ru.team.todo.repository;

import ru.team.todo.domain.Task;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task);

    void removeTask(int id);

    Task getTaskById(int id);

    List<Task> getAllTasksByUserId(int id);

    List<Task> getAllTasks();
}
