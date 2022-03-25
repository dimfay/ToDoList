package ru.team.todo.repository;

import ru.team.todo.domain.Task;

import java.util.List;

public interface TaskRepository {
    void addTask();
    List<Task> getAllTasks();


}
