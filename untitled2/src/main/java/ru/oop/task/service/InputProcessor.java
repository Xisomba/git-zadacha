package ru.oop.task.service;

public class InputProcessor {
    public String[] InputProcessor(String consoleInput) {
        String input = consoleInput.trim();
        if (input == null || input.isEmpty()) {
            System.out.println("Empty command! Type HELP for usage");
        } else if (!input.contains(" ")) {
            System.out.println("Wrong writing. Try again.");
        } else {
            String[] words = input.replace(", ", ",").split(" ");
        }
    }
}