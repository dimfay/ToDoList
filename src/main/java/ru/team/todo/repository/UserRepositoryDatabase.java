package ru.team.todo.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.User;

import java.util.Collection;
import java.util.List;

@Repository
public class UserRepositoryDatabase implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(String name) {
        String query = "INSERT INTO users (name) VALUES (?)";
        this.jdbcTemplate.update(query, name);
    }

    @Override
    public void removeUser(String name) {
        String query = "DELETE FROM users WHERE name = ?";
        this.jdbcTemplate.update(query, name);
    }

    @Override
    public User getUserByName(String name) {
        String query = "SELECT name FROM users WHERE name = ?";
        List<User> tmp = this.jdbcTemplate.query(query, new Object[]{name}, new BeanPropertyRowMapper<>(User.class));
        if (tmp.isEmpty()) {
            return null;
        }
        return tmp.get(0);
    }

    @Override
    public Collection<User> getAllUsers() {
        String query = "SELECT name FROM users;";
        return this.jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }
}
