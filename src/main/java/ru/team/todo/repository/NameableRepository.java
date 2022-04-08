package ru.team.todo.repository;

public interface NameableRepository<DATA, ID> extends Repository<DATA, ID> {

    DATA findByName(String name);

}
