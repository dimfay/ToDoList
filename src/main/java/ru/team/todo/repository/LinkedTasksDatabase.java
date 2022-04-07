package ru.team.todo.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.team.todo.domain.LinkedTask;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collection;

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
    public Collection<? extends LinkedTask> findAll() {
        CriteriaBuilder cb = this.sessionFactory.getCriteriaBuilder();
        CriteriaQuery<LinkedTask> criteria = cb.createQuery(LinkedTask.class);
        Root<LinkedTask> userRoot = criteria.from(LinkedTask.class);
        criteria.select(userRoot);
        return this.sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }
}
