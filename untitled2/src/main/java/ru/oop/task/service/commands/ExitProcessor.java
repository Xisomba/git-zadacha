package ru.oop.task.service.commands;

public class ExitProcessor {
    public static boolean isCommandExit(String[] command) {
        return command.length == 1 && command[0].equalsIgnoreCase("EXIT");

    }

}
