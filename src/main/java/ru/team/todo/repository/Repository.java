package ru.team.todo.repository;


import java.util.Collection;

public interface Repository<T> {

    T addData(T data);

    void removeData(String name);

    T getDataByName(String name);

    Collection<? extends T> getAllData();


}
