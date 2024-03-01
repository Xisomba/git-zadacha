package ru.oop.task.service.commands;
import ru.oop.task.repo.PetRepository;
import ru.oop.task.repo.UserRepository;
import ru.oop.task.service.DatabaseService;
public class UpdateProcessor {
    public static String updateCommand(String[] words, PetRepository petRepository, UserRepository userRepository) {
        if (DatabaseService.hasDb(words[1])) {
            if (!words[2].equalsIgnoreCase("SET") || !words[4].equalsIgnoreCase("WHERE")) {
                return """
                        Неправильно оформлена команда UPDATE.
                                       
                        Корректный ввод -> UPDATE {Название таблицы} SET {поле1=значение1, поле2=значение2, ...} WHERE {условие}
                                           
                        """;
            }
        }
        return createUpdateOutput(words, userRepository, petRepository);

    }

    public static String createUpdateOutput(String[] words, UserRepository userRepository, PetRepository petRepository) {
        if (words[1].equalsIgnoreCase("users")) {
            return updateUsers(words, userRepository);
        } else if (words[1].equalsIgnoreCase("pets")) {
            return updatePets(words, petRepository);
        }
        return "..";
    }

    private static String updateUsers(String[] words, UserRepository userRepository) {
        if (userRepository.updateSelectedUsers(words)) {
            return "Данные пользователей успешно обновлены.";
        } else {
            return "Ошибка при обновлении данных пользователей.";
        }

    }

    private static String updatePets(String[] words, PetRepository petRepository) {
        if (petRepository.updateSelectedPets(words)) {
            return "Данные питомцев успешно обновлены.";
        } else {
            return "Ошибка при обновлении данных питомцев.";
        }
    }
}


