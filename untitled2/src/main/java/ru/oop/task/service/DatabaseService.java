package ru.oop.task.service;

import ru.oop.task.model.Database;
import ru.oop.task.model.Pet;
import ru.oop.task.model.User;
import ru.oop.task.repo.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class DatabaseService {
    private static final Map<String, Database<?>> dbmap = new HashMap<>();

    public void init() {
        if (!dbmap.containsKey("users") && !dbmap.containsKey("pets")) {
            dbmap.put("users", new Database<User>());
            dbmap.put("pets", new Database<Pet>());
        }
    }

    public static String createDatabase(String[] words) {
        dbmap.put(words[2], new Database<User>());
        return String.format("Table %s created with columns (id, first_name, second_name, age, pet_id)",words[2]);
    }

    public static boolean hasDb(String name) {
        return dbmap.containsKey(name);
    }
}
