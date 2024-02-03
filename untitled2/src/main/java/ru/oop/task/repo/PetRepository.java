package ru.oop.task.repo;

import ru.oop.task.model.Database;
import ru.oop.task.model.Pet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PetRepository extends MainRepository<Pet> {
    public PetRepository() {
        super(new Database<>());
    }
    public boolean checkIfFieldExist (String field){ //список для проверка есть ли вообще такая команда
        List <String> existingFields = new ArrayList<>();
        existingFields.add("id");
        existingFields.add("name");
        existingFields.add("breed");
        existingFields.add("age");
        if (existingFields.contains(field)) {
            return true;
        } else {
            System.out.println("Поле " + field + " не существует.");
        }
        System.out.println("Поле " + field + " не существует.");
        return false;
    }

    public  String compareFieldValue (String [] words){
        String condition =words[4].replaceAll("[a-zA-Z0-9\\s]", "");
        List<Pet> petList = getAll();
        String  fieldName = words[4].split(condition)[0];   // id=2
        String value = words[4].split(condition)[1];
        for (Pet pet: petList)
        switch (condition) {
            case ("=") -> ;
            case ("!=") -> ;
            case (">=") -> ;
            case ("<=") -> ;
        }
    }
}