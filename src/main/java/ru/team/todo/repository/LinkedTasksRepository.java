package ru.team.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.team.todo.domain.LinkedTask;

public interface LinkedTasksRepository extends JpaRepository<LinkedTask, Integer> {

}
