package ru.team.todo.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.Task;

import java.util.List;

@Repository
public class TaskRepositoryDatabase implements TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public TaskRepositoryDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Task> getAllTasks() {
        return jdbcTemplate.query("SELECT * FROM task", new BeanPropertyRowMapper(Task.class));
    }

    @Override
    public void addTask() {

    }
}
