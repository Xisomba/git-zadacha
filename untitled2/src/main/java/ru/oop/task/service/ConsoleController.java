package ru.oop.task.service;

import ru.oop.task.repo.MainRepository;
import ru.oop.task.repo.PetRepository;
import ru.oop.task.repo.UserRepository;
import ru.oop.task.service.commands.ExitProcessor;
import ru.oop.task.service.commands.HelpProcessor;
import ru.oop.task.service.commands.InsertProcessor;
import ru.oop.task.service.commands.UpdateProcessor;

import java.util.HashMap;
import java.util.Map;

public class ConsoleController {
    // репы
    private final Map<String, MainRepository<?>> repositories = new HashMap<>();
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public ConsoleController() {
        this.petRepository = new PetRepository();
        this.userRepository = new UserRepository();
    }

    public void handleCommand(String[] consoleInput) {
        String[] words = InputProcessor.processInput(consoleInput);

        if (words.length == 0) {
            // слова не были получены
            return;
        }

        switch (words[0].toUpperCase()) {
            case "CREATE" -> DatabaseService.createDatabase(words);
            // case "SELECT" -> ;
            case "INSERT" -> InsertProcessor.insertCommand(words, petRepository, userRepository);
            // case "DELETE" -> ;
            case "UPDATE" -> UpdateProcessor.updateCommand(words, petRepository, userRepository);
            case "HELP" -> HelpProcessor.helpRequested(words);
            case "EXIT" -> ExitProcessor.isCommandExit(words);
            default -> System.out.println("Unknown command. Type HELP for usage.");
        }
    }
}

