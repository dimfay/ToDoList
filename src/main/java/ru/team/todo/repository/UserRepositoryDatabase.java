package ru.team.todo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.User;

import java.util.Collection;

@Repository
public class UserRepositoryDatabase implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addUser(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeUser(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getUserByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<User> getAllUsers() {
        throw new UnsupportedOperationException();
    }
}
