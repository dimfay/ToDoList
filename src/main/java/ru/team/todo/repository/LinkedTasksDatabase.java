package ru.team.todo.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.LinkedTask;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class LinkedTasksDatabase implements LinkedTasksRepository {
    private final SessionFactory sessionFactory;

    LinkedTasksDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(LinkedTask linkedTask) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(linkedTask);
    }

    @Override
    public void remove(LinkedTask linkedTask) {
        this.sessionFactory.getCurrentSession().remove(linkedTask);
    }

    @Override
    public LinkedTask findById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(LinkedTask.class, id);
    }

    @Override
    public LinkedTask findLinkedTaskByTasksId(int firstTask, int secondTask) {
        List<LinkedTask> linkedTasks = this.sessionFactory.getCurrentSession()
                .createQuery("FROM LinkedTask WHERE taskId = :taskId AND linkedTaskId = :linkedTaskId", LinkedTask.class)
                .setParameter("taskId", firstTask)
                .setParameter("linkedTaskId", secondTask)
                .getResultList();
        if (linkedTasks == null) {
            return null;
        }
        if (linkedTasks.isEmpty()) {
            return null;
        }

        return linkedTasks.get(0);
    }

    @Override
    public Collection<LinkedTask> findAll() {
        CriteriaBuilder cb = this.sessionFactory.getCriteriaBuilder();
        CriteriaQuery<LinkedTask> criteria = cb.createQuery(LinkedTask.class);
        Root<LinkedTask> userRoot = criteria.from(LinkedTask.class);
        criteria.select(userRoot);
        return this.sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }

}
