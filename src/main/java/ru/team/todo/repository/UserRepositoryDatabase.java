package ru.team.todo.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        return this.sessionFactory.getCurrentSession().byNaturalId(User.class).using("name", name).load();
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder cb = this.sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);
        return this.sessionFactory.getCurrentSession().createQuery(criteria).getResultList();

    }
}
