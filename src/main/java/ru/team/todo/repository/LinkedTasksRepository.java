package ru.team.todo.repository;

import ru.team.todo.domain.LinkedTask;

import java.util.List;

public interface LinkedTasksRepository {

    void addLinkedTasks(LinkedTask linkedTasks);

    void removeLinkedTasks(LinkedTask linkedTasks);

    LinkedTask getLinkedTasksByTaskId(int id);

    List<LinkedTask> getAllLinkedTasks();

}
