package ru.oop.task.model;

import java.util.*;

public class Database<T> {
    private Map<Integer, T> map;
    private int currentId = 0;

    public Database() {
        this.map = new HashMap<>();
    }


    public void insert(T entity) {
        currentId++;
        map.put(currentId, entity);
    }

    public void update(T entity) {
    }

    public void delete(Integer id) {
        map.remove(id);
    }

    public List<T> selectAll() {

        return new ArrayList<>(map.values());
    }

    public Integer getId(T entity) {
        for (Map.Entry<Integer, T> entry : map.entrySet()) {
            if (entity.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}