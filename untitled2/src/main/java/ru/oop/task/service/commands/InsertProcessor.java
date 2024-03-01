package ru.oop.task.service.commands;

import ru.oop.task.model.Pet;
import ru.oop.task.model.User;
import ru.oop.task.repo.PetRepository;
import ru.oop.task.repo.UserRepository;
import ru.oop.task.service.DatabaseService;

public class InsertProcessor {
    public static String insertCommand(String[] words, PetRepository petRepository, UserRepository userRepository) {
        if (DatabaseService.hasDb(words[2])) {
            String[] valueAndValues = words[3].split("\\(");
            String[] values = valueAndValues[1].replace(")", "").split(",");
            if (!words[1].equalsIgnoreCase("INTO") || !valueAndValues[0].equalsIgnoreCase("VALUES")
                    || values.length < 3 || values.length > 4) {
                return """
                        Неправильно оформлена команда INSERT.
                                       
                        Корректный ввод -> INSERT INTO {Название базы данных} VALUES({Значение}, {Значение}...)
                                           
                        """;
            } else {
                if (words[2].equalsIgnoreCase("pets")) {
                    Pet pet = new Pet(values[0].replace("\"", ""),
                            values[1].replace("\"", ""), Integer.parseInt(values[2]));
                    petRepository.insert(pet);
                    return String.format("Inserted into %s with values (id=%d, first_name=%s, breed=%s, age=%d",
                            words[2], petRepository.getId(pet), pet.getName(), pet.getBreed(), pet.getAge());
                } else if (words[2].equalsIgnoreCase("users")){
                    User user = new User(values[0].replace("\"", ""),
                            values[1].replace("\"", ""), Integer.parseInt(values[2]));
                    userRepository.insert(user);
                    if (values.length == 4) {
                        user.setPetId(Integer.parseInt(values[3]));
                        return String.format("Inserted into %s with values (id=%d, first_name=%s, second_name=%s, age=%d, pet_id=%d",
                                words[2], user.getId(), user.getFirstName(), user.getSecondName(), user.getAge(), user.getPetId());
                    } else {
                        return String.format("Inserted into %s with values (id=%d, first_name=%s, second_name=%s, age=%d",
                                words[2], userRepository.getId(user), user.getFirstName(), user.getSecondName(), user.getAge());
                    }
                }
                else {
                    UserRepository otherRepository = new UserRepository();
                    User otherEntity = new User(values[0].replace("\"", ""),
                            values[1].replace("\"", ""), Integer.parseInt(values[2]));
                    otherRepository.insert(otherEntity);
                    return String.format("Inserted into %s with values (id=%d, first_name=%s, second_name=%s, age=%d",
                            words[2], otherRepository.getId(otherEntity), otherEntity.getFirstName(), otherEntity.getSecondName(), otherEntity.getAge());
                }
            }
        }
        return String.format("Отсутствует база данных с таким названием : %s",words[2]);
    }
    private InsertProcessor(){}
}
