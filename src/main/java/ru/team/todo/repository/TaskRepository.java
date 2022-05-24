package ru.team.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.team.todo.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByName(String name);
}
