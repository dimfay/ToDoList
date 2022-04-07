package ru.team.todo.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.LinkedTask;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LinkedTasksDatabase implements LinkedTasksRepository {
    private final SessionFactory sessionFactory;

    LinkedTasksDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addLinkedTasks(LinkedTask linkedTasks) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(linkedTasks);
    }

    @Override
    public void removeLinkedTasks(LinkedTask linkedTasks) {
        this.sessionFactory.getCurrentSession().remove(linkedTasks);
    }

    @Override
    public LinkedTask getLinkedTasksByTaskId(int id) {
        return null;
    }

    @Override
    public List<LinkedTask> getAllLinkedTasks() {
        CriteriaBuilder cb = this.sessionFactory.getCriteriaBuilder();
        CriteriaQuery<LinkedTask> criteria = cb.createQuery(LinkedTask.class);
        Root<LinkedTask> userRoot = criteria.from(LinkedTask.class);
        criteria.select(userRoot);
        return this.sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }
}
