package ru.team.todo.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryDatabase implements UserRepository {

    private final SessionFactory sessionFactory;

    UserRepositoryDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void removeUser(User user) {
        this.sessionFactory.getCurrentSession().remove(user);
    }

    @Override
    public User getUserByName(String name) {
        return this.sessionFactory.getCurrentSession().get(User.class, name);
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }
}
