package ru.team.todo.repository;

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
        this.sessionFactory.openSession().saveOrUpdate(task);
    }

    @Override
    public void remove(Task task) {
        this.sessionFactory.openSession().remove(task);
    }

    @Override
    public Task findById(Integer id) {
        return this.sessionFactory.openSession().get(Task.class, id);
    }

    @Override
    public Task findByName(String name) {
        return this.sessionFactory.openSession().byNaturalId(Task.class).using("name", name).load();
    }

    @Override
    public Collection<Task> findAll() {
        CriteriaBuilder cb = this.sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
        Root<Task> userRoot = criteria.from(Task.class);
        criteria.select(userRoot);
        return this.sessionFactory.openSession().createQuery(criteria).getResultList();
    }

}
