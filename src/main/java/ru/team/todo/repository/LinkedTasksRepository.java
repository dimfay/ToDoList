package ru.team.todo.repository;

import ru.team.todo.domain.LinkedTask;

public interface LinkedTasksRepository extends Repository<LinkedTask, Integer> {

    LinkedTask findLinkedTaskByTasksId(int firstTask, int secondTask);

}
