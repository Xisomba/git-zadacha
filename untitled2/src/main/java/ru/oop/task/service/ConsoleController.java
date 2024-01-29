package ru.oop.task.service;

import ru.oop.task.repo.MainRepository;
import ru.oop.task.service.commands.ExitProcessor;
import ru.oop.task.service.commands.InsertProcessor;

import java.util.HashMap;
import java.util.Map;

public class ConsoleController {
    private final Map<String, MainRepository<?>> repositories = new HashMap<>();

    public void handleCommand(String[] words) {
        if (ExitProcessor.exitRequested(words)) {
            System.out.println("Exiting program...");
            return;
        }

        String output;
        switch (words[0].toUpperCase()) {
            case "CREATE" -> output = DatabaseService.createDatabase(words);
            case "SELECT" -> ;
            case "INSERT" -> output = InsertProcessor.insertCommand(words);
            case "DELETE" -> ;
            case "UPDATE" -> ;
            case "HELP" -> ;
            case "EXIT" ->
            default -> System.out.println("Unknown command. Type HELP for usage.");

        }

    }
}