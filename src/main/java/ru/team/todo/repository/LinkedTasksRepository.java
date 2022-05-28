package ru.team.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.team.todo.domain.LinkedTask;
import ru.team.todo.domain.Task;

import java.util.List;

public interface LinkedTasksRepository extends JpaRepository<LinkedTask, Integer> {

    LinkedTask findByTaskIdAndLinkedTaskId(int taskId, int linkedTaskId);

    List<LinkedTask> findAllByTask(Task task);

    List<LinkedTask> findAllByLinkedTask(Task task);

}
