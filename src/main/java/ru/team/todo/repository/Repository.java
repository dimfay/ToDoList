package ru.team.todo.repository;

import java.util.Collection;

public interface Repository<DATA, ID> {

    void add(DATA data);
    void remove(DATA data);
    DATA findById(ID id);
    Collection<? extends DATA> findAll();

}
