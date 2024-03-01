package ru.oop.task.service;

public class InputProcessor {
    public static String[] processInput(String[] consoleInput) {
        if (consoleInput == null || consoleInput.length == 0) {
            System.out.println("Empty command! Type HELP for usage");
            return new String[0];
        }

        String input = String.join(" ", consoleInput); // Объединяем массив в одну строку
        if (input.isEmpty()) {
            System.out.println("Empty command! Type HELP for usage");
            return new String[0];
        } else if (!input.contains(" ")) {
            System.out.println("Wrong writing. Try again.");
            return new String[0];
        } else {
            return input.replace(", ", ",").split(" ");
        }
    }
}

