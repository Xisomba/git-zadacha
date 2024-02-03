package ru.oop.task.service.commands;

import ru.oop.task.model.Pet;
import ru.oop.task.model.User;
import ru.oop.task.repo.PetRepository;
import ru.oop.task.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class SelectProcessor {
    public static String selectCommand(String[] words) {
        if ((!words[1].equals("*") && !words[1].equalsIgnoreCase("FROM")
                || (!words[2].equalsIgnoreCase("FROM") && !words[3].equalsIgnoreCase("WHERE")))) {
            return """
                    Неправильно оформлена команда INSERT.
                                   
                    Корректный ввод -> INSERT INTO {Название базы данных} VALUES({Значение}, {Значение}...)
                                       
                    """;
        } else {
            return createOutput(words);
        }
    }

    private static String createOutput(String[] words) {
        List<String> outputList = new ArrayList<>();
        if (words[1].equals("*") && words.length == 4 && words[2].equalsIgnoreCase("FROM")) {
            if (words[3].equalsIgnoreCase("pets")) {
                PetRepository repository = new PetRepository();
                outputList.add("""
                        |id|name|breed|age|
                        +--+----+-----+---+\s
                        """);
                List<Pet> petList = repository.getAll();
                for (Pet pet : petList) {

                    outputList.add("""
                            |%d|  %s|   %s| %d|
                            +--+----+-----+---+ \s
                            """.formatted(pet.getId(), pet.getName(), pet.getBreed(), pet.getAge()));
                }
            } else {
                UserRepository repository = new UserRepository();
                outputList.add("""
                        |id|first_name|second_name|age|pet_id|
                        +--+----------+-----------+---+------+
                        """);
                List<User> userList = repository.getAll();
                for (User user : userList) {
                    outputList.add("""
                            |%d|        %s|         %s| %d|    %s|
                            +--+----------+-----------+---+------+  \s
                            """.formatted(user.getId(), user.getFirstName(), user.getSecondName(), user.getAge(), user.getPetId() == null ? "" : user.getPetId().toString()));
                }

            }
            return String.join("", outputList);
        } else if (words[1].equalsIgnoreCase("FROM") && words.length == 5 && words[3].equalsIgnoreCase("WHERE")// добавить проверку на поле
                && (words[4].contains("=") || words[4].contains("!=") || words[4].contains(">=") || words[4].contains("<="))) {
            PetRepository repository = new PetRepository();

            if (repository.checkIfFieldExist(words[4]) &&words[2].equalsIgnoreCase("pets")) {                // неверное условие вернуть хуйню
                outputList.add("""
                        |id|name|breed|age|
                        +--+----+-----+---+
                        """ );

                List<Pet> selectedPets = repository.
            }
                else  if (){

                }
            }
        }
    }
}

