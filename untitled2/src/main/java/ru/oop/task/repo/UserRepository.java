package ru.oop.task.repo;

import ru.oop.task.model.Database;
import ru.oop.task.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserRepository extends MainRepository<User> {
    public UserRepository() {
        super(new Database<>());
    }
    public   boolean checkIfFieldExist (String field){ //список для проверка есть ли вообще такая команда
        List <String> existingFields = new ArrayList<>();
        existingFields.add("id");
        existingFields.add("name");
        existingFields.add("secondName");
        existingFields.add("age");
        existingFields.add("petId");
        if (existingFields.contains(field)) {
return true;
        } else {
            System.out.println("Поле " + field + " не существует.");
        }
        System.out.println("Поле " + field + " не существует.");
        return false;

    }

public  static String getFieldValueString ()
}