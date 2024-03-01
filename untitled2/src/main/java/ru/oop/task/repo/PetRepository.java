package ru.oop.task.repo;

import ru.oop.task.model.Database;
import ru.oop.task.model.Pet;
import ru.oop.task.model.User;
import ru.oop.task.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PetRepository extends MainRepository<Pet> {
    public PetRepository() {
        super(new Database<>());
    }

    public boolean checkIfFieldExist(String field) { //список для проверки есть ли вообще такая команда
        List<String> existingFields = new ArrayList<>();
        existingFields.add("id");
        existingFields.add("name");
        existingFields.add("breed");
        existingFields.add("age");
        if (existingFields.contains(field)) {
            return true;
        }
        System.out.println("Поле " + field + " не существует.");
        return false;
    }

    public List<Pet> getFilteredPets(String[] words) {
        String condition = words[4].replaceAll("[a-zA-Z0-9\\s]", "");
        List<Pet> petList = getAll();
        String fieldName = words[4].split(condition)[0];   // id=2
        String value = words[4].split(condition)[1];
        if (!checkIfFieldExist(fieldName)) {
            System.out.println("Поле " + fieldName + " не существует.");

            return null;
        }
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : petList) {
            if (checkCondition(condition, value, pet.getFieldValue(fieldName))) {
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }

    public boolean checkCondition(String condition, Object value, Object fieldValue) {
        if (!"=".equals(condition) && !"!=".equals(condition) && !isBothNumeric(value, fieldValue)) {
            System.out.println("В вашем сравнении оба параметра должны быть числами.");
            return false;
        }
        switch (condition) {
            case (">") -> {
                return (double) fieldValue > (double) value;
            }
            case ("<") -> {
                return (double) fieldValue < (double) value;
            }
            case ("=") -> {
                return (double) fieldValue == (double) value;
            }
            case ("!=") -> {
                return (double) fieldValue != (double) value;
            }
            case (">=") -> {
                return (double) fieldValue >= (double) value;
            }
            case ("<=") -> {
                return (double) fieldValue <= (double) value;
            }
            default -> {
                return false;
            }
        }
    }

    private boolean isBothNumeric(Object first, Object second) {
        return StringUtils.isNumeric(first.toString()) && StringUtils.isNumeric(second.toString());
    }

    public boolean deleteSelectedPets(String[] words) {
        List<Pet> petsToDelete = getFilteredPets(words);
        if (petsToDelete != null && !petsToDelete.isEmpty()) {
            for (Pet pet : petsToDelete) {
                delete(pet.getId());
                System.out.printf("Delete from %s. Deleted values is (id=%d, name=%s, breed=%s, age=%d)", words[2], pet.getId(), pet.getName(), pet.getBreed(), pet.getAge() );
            }
            return true;
        } else {
            System.out.println("No rows to delete");
            return false;
        }
    }
    public boolean updateSelectedPets(String[] words) {
        List<Pet> petsToUpdate = getFilteredPets(words);
        if (petsToUpdate != null && !petsToUpdate.isEmpty()) {
            String fieldToUpdate = words[6];
            String valueToUpdate = words[8];
            for (Pet pet : petsToUpdate) {
                switch (words[6]) {
                    case "age" ->   pet.setAge(Integer.parseInt(valueToUpdate));
                    case "name" -> pet.setName(valueToUpdate);
                    case "breed" -> pet.setBreed(valueToUpdate);
                    case "id" -> pet.setId(Integer.parseInt(valueToUpdate));
                    default -> {
                    }
                }
                // Save changes
                update(pet);
                System.out.printf("Updated pet %s. New %s is %s.\n", pet.getName(), fieldToUpdate, valueToUpdate);
            }
            return true;
        } else {
            System.out.println("No pets to update.");
            return false;
        }
    }
}
