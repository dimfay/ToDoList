package ru.team.todo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.Task;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class TaskRepositoryDatabase implements TaskRepository {
    private final SessionFactory sessionFactory;

    TaskRepositoryDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Task task) {
        try (Session session = this.sessionFactory.openSession()) {
            session.saveOrUpdate(task);
        }
    }

    @Override
    public void remove(Task task) {
        try (Session session = this.sessionFactory.openSession()) {
            session.remove(task);
        }
    }

    @Override
    public Task findById(Integer id) {
        try (Session session = this.sessionFactory.openSession()) {
            return session.get(Task.class, id);
        }
    }

    @Override
    public Task findByName(String name) {
        try (Session session = this.sessionFactory.openSession()) {
            return session.byNaturalId(Task.class).using("name", name).load();
        }
    }

    @Override
    public Collection<Task> findAll() {
        try (Session session = this.sessionFactory.openSession()) {
            CriteriaBuilder cb = this.sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
            Root<Task> userRoot = criteria.from(Task.class);
            criteria.select(userRoot);
            return session.createQuery(criteria).getResultList();
        }
    }

}
