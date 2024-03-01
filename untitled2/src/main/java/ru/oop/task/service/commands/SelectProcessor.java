package ru.oop.task.service.commands;

import ru.oop.task.model.Pet;
import ru.oop.task.model.User;
import ru.oop.task.repo.PetRepository;
import ru.oop.task.repo.UserRepository;
import ru.oop.task.service.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class SelectProcessor {
    public static String selectCommand(String[] words) {
        if (!words[1].equalsIgnoreCase("FROM") || !words[3].equalsIgnoreCase("WHERE")) {
            return """
                    Неправильно оформлена команда SELECT.
                                   
                    Корректный ввод -> SELECT * FROM {Название таблицы} WHERE {условие}
                                       
                    """;
        } else {
            return createOutput(words);
        }
    }

    private static String createOutput(String[] words) {
        List<String> outputList = new ArrayList<>();
        if (words[1].equalsIgnoreCase("FROM") && words[3].equalsIgnoreCase("WHERE")
                && (words[4].contains("=") || words[4].contains("!=") || words[4].contains(">=") || words[4].contains("<="))) {
            PetRepository petRepository = new PetRepository();
            UserRepository userRepository = new UserRepository();

            if (petRepository.checkIfFieldExist(words[2])) {
                List<Pet> selectedPets = petRepository.getFilteredPets(words);
                if (selectedPets != null && !selectedPets.isEmpty()) {
                    outputList.add("""
                        | id | name | breed | age |
                        +----+------+------+-----+\s
                        """);
                    for (Pet pet : selectedPets) {
                        outputList.add(String.format("| %d  | %s | %s | %d |\s", pet.getId(), pet.getName(), pet.getBreed(), pet.getAge()));
                    }
                } else {
                    outputList.add("No pets match the specified conditions.");
                }
            } else if (userRepository.checkIfFieldExist(words[2])) {
                List<User> selectedUsers = userRepository.getFilteredUsers(words);
                if (selectedUsers != null && !selectedUsers.isEmpty()) {
                    outputList.add("""
                        | id | first_name | second_name | age | pet_id |
                        +----+------------+-------------+-----+--------+
                        """);
                    for (User user : selectedUsers) {
                        outputList.add(String.format("| %d  | %s | %s | %d | %s |\s", user.getId(), user.getFirstName(),
                                user.getSecondName(), user.getAge(), user.getPetId() == null ? "" : user.getPetId()));
                    }
                } else {
                    outputList.add("No users match the specified conditions.");
                }
            } else {
                outputList.add("Invalid table name or field name.");
            }
        } else {
            outputList.add("Incorrect WHERE clause.");
        }
        return String.join("", outputList);
    }
}


