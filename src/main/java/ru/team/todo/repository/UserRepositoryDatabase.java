package ru.team.todo.repository;

import org.springframework.stereotype.Repository;
import ru.team.todo.domain.User;

import java.util.Collection;

//TODO Реализовать SQL запросы и доступ к бд здесь.
//TODO Теперь встал вопрос насчет тудушки самого пользователя, похоже для него так же придется делать репозиторий.
@Repository
public class UserRepositoryDatabase implements UserRepository {

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
