package ru.oop.task.repo;

import java.util.List;

public interface BaseRepository<T> {
    void create(T entity);
    T getById(Integer id);
    List<T> getAll();
    void update(T entity);
    void delete(Integer id);
}
