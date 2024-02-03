package ru.oop.task.repo;
import ru.oop.task.model.Database;

import java.util.List;

public class MainRepository<T> implements BaseRepository<T> {
    private final Database<T> database;

    public MainRepository(Database<T> database) {
        this.database = database;
    }

    public void create(T entity) {
        database.insert(entity);
    }

    public T getById(Integer id) {
        return null;
    }

    public List<T> getAll() {
        return database.selectAll();
    }

    public void update(T entity) {

    }

    public void delete(Integer id) {

    }

    public int getId(T entity){
        return database.getId(entity);
    }

}