package ru.team.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.team.todo.domain.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByName(String name);
    List<Task> findAllByUserName(@Param("username") String username);
}
