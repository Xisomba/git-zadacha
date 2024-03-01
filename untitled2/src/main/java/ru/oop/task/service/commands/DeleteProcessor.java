
package ru.oop.task.service.commands;

import ru.oop.task.model.Pet;
import ru.oop.task.model.User;
import ru.oop.task.repo.PetRepository;
import ru.oop.task.repo.UserRepository;
import ru.oop.task.service.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class DeleteProcessor {
    public  static String deleteCommand (String[] words, UserRepository userRepository, PetRepository petRepository){
    if (DatabaseService.hasDb(words[2])){
        if (!words[1].equalsIgnoreCase("FROM") || !words[3].equalsIgnoreCase("WHERE")){
            return """
                        Неправильно оформлена команда DELETE.
                                       
                        Корректный ввод -> DELETE FROM {Название базы данных} WHERE {условие}
                                           
                        """;
        }
    }
        return  createDeleteOutput(words, petRepository,userRepository);

    }
    public static String createDeleteOutput (String[] words, PetRepository petRepository, UserRepository userRepository){
        List <String> outputList = new ArrayList<>();
        if ( words.length == 4 && words[1].equalsIgnoreCase("FROM")) {
            List<Pet> petsToDelete = petRepository.getFilteredPets(words);
            if (petsToDelete != null && !petsToDelete.isEmpty()) {
                for (Pet pet : petsToDelete) {
                    petRepository.delete(pet.getId());
                    outputList.add(String.format("Deleted from %s. Deleted values are (id=%d, name=%s, breed=%s, age=%d).\n",
                            words[2], pet.getId(), pet.getName(), pet.getBreed(), pet.getAge()));
                }
            } else {
                outputList.add("No pets match the specified conditions to delete.");
            }
        } else if (words[2].equalsIgnoreCase("users")) {
            List<ru.oop.task.model.User> usersToDelete = userRepository.getFilteredUsers(words);
            if (usersToDelete != null && !usersToDelete.isEmpty()) {
                for (ru.oop.task.model.User user : usersToDelete) {
                    userRepository.delete(user.getId());
                    outputList.add(String.format("Deleted from %s. Deleted values are (id=%d, first_name=%s, second_name=%s, age=%d, pet_id=%s).\n",
                            words[2], user.getId(), user.getFirstName(), user.getSecondName(), user.getAge(),
                            user.getPetId() == null ? "" : user.getPetId().toString()));
                }
            } else {
                outputList.add("No users match the specified conditions to delete.");
            }
        } else {
            outputList.add("Invalid table name.");
        }
        return String.join("", outputList);
    }
}

