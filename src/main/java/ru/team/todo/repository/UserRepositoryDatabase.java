package ru.team.todo.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class UserRepositoryDatabase implements UserRepository {

    private final SessionFactory sessionFactory;

    UserRepositoryDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        this.sessionFactory.openSession().saveOrUpdate(user);
    }

    @Override
    public void remove(User user) {
        this.sessionFactory.openSession().remove(user);
    }

    @Override
    public User findById(Integer id) {
        return this.sessionFactory.openSession().get(User.class, id);
    }

    @Override
    public User findByName(String name) {
        return this.sessionFactory.openSession().byNaturalId(User.class).using("name", name).load();
    }

    @Override
    public Collection<User> findAll() {
        CriteriaBuilder cb = this.sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);
        return this.sessionFactory.openSession().createQuery(criteria).getResultList();
    }
}
