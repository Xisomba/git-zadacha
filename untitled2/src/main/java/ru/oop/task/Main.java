package ru.oop.task;

import ru.oop.task.service.ConsoleController;
import ru.oop.task.service.DatabaseService;
import ru.oop.task.service.commands.ExitProcessor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        databaseService.init();
        ConsoleController consoleHandler = new ConsoleController();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            String[] words = command.split("\\s+"); // Разделяем строку на слова по пробелам
            if (ExitProcessor.isCommandExit(words)) {
                System.out.println("Exiting program...");
                break;
            }
            consoleHandler.handleCommand(words);
        }
    }
}
