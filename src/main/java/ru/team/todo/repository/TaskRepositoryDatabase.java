package ru.team.todo.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.Task;

import java.util.List;

//TODO Будем переделывать
@Repository
public class TaskRepositoryDatabase implements TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public TaskRepositoryDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addTask(Task task) {
        String query = "INSERT INTO tasks (userId, name, description, linkedTaskId)";
        //TODO Придумать способ передавать сюда ид юзера и привязанную задачу. Похоже придется переписать класс Task и User.
        this.jdbcTemplate.update(query, 1, task.getName(), task.getDescription(), 1);
    }

    @Override
    public void removeTask(int id) {
        String query = "DELETE FROM tasks WHERE id = ?";
        this.jdbcTemplate.update(query, id);
    }

    @Override
    public Task getTaskById(int id) {
        String query = "SELECT id, userId, name, description, linkedTaskId FROM tasks WHERE id = ?";
        List<Task> tmp = this.jdbcTemplate.query(query, new Object[]{id}, new BeanPropertyRowMapper<>(Task.class));
        if (tmp.isEmpty()) {
            return null;
        }

        return tmp.get(0);
    }

    @Override
    public List<Task> getAllTasksByUserId(int id) {
        String query = "SELECT id, userId, name, description, linkedTaskId FROM tasks WHERE userId = ?";
        return this.jdbcTemplate.query(query, new Object[]{id}, new BeanPropertyRowMapper<>(Task.class));
    }

    @Override
    public List<Task> getAllTasks() {
        String query = "SELECT id, userId, name, description, linkedTaskId FROM tasks";
        return this.jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Task.class));
    }

}
