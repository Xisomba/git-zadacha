package ru.oop.task.repo;

import ru.oop.task.model.Database;
import ru.oop.task.model.User;
import ru.oop.task.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

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

    public List <User> getFilteredUsers(String[] words) {
        String condition = words[4].replaceAll("[a-zA-Z0-9\\s]", "");
        List<User> userList = getAll();
        String fieldName = words[4].split(condition)[0];   // id=2
        String value = words[4].split(condition)[1];
        if (!checkIfFieldExist(fieldName)) {
            System.out.println("Поле " + fieldName + " не существует.");

            return null;
        }
        List <User> filteredUsers = new ArrayList<>();
        for (User user : userList) {
            if (checkCondition(condition, value, user.getFieldValue(fieldName))){
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    public boolean checkCondition(String condition, Object value, Object fieldValue) {
        if (!"=".equals(condition) && !"!=".equals(condition) && !isBothNumeric(value, fieldValue)) {
            System.out.println("В вашем сравнении оба параметра должны быть числами.");
            return false;
        }
        switch (condition) {
            case (">") -> {
                return  (double)fieldValue > (double)value ;
            }
            case ("<") -> {
                return (double)fieldValue < (double)value;
            }
            case ("=") -> {
                return (double)fieldValue == (double)value;
            }
            case ("!=") -> {
                return (double)fieldValue != (double)value;
            }
            case (">=") -> {
                return (double)fieldValue >= (double)value;
            }
            case ("<=") -> {
                return (double)fieldValue <= (double)value;
            }
            default -> {
                return false;
            }
        }
    }
    public boolean updateSelectedUsers(String[] words) {
        List<User> usersToUpdate = getFilteredUsers(words);
        if (usersToUpdate != null && !usersToUpdate.isEmpty()) {
            String fieldToUpdate = words[6];
            String valueToUpdate = words[8];
            for (User user : usersToUpdate) {
                switch (words[6]) {
                 case "age" ->   user.setAge(Integer.parseInt(valueToUpdate));
                 case "first_name" -> user.setFirstName(valueToUpdate);
                 case "second_name" -> user.setSecondName(valueToUpdate);
                 case "id" -> user.setId(Integer.parseInt(valueToUpdate));
                 case "pet_id" -> user.setPetId(Integer.parseInt(valueToUpdate));
                    default -> {
                    }
                }
                // Save changes
                update(user);
                System.out.printf("Updated user %s. New %s is %s.\n", user.getFirstName(), fieldToUpdate, valueToUpdate);
            }
            return true;
        } else {
            System.out.println("No users to update.");
            return false;
        }
    }
    private boolean isBothNumeric(Object first, Object second){
        return StringUtils.isNumeric(first.toString()) && StringUtils.isNumeric(second.toString());
    }

}