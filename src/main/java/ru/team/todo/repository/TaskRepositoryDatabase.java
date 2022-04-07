package ru.team.todo.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.Task;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryDatabase implements TaskRepository {
    private final SessionFactory sessionFactory;

    TaskRepositoryDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTask(Task task) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(task);
    }

    @Override
    public void removeTask(Task task) {
        this.sessionFactory.getCurrentSession().remove(task);
    }

    @Override
    public Task getTaskById(int id) {
        return this.sessionFactory.getCurrentSession().get(Task.class, id);
    }

    @Override
    public Task getTaskByName(String name) {
        return this.sessionFactory.getCurrentSession().byNaturalId(Task.class).using("name", name).load();
    }

    @Override
    public List<Task> getAllTasks() {
        CriteriaBuilder cb = this.sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
        Root<Task> userRoot = criteria.from(Task.class);
        criteria.select(userRoot);
        return this.sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }

}
