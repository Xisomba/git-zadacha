package ru.oop.task.service.commands;

import java.util.HashMap;
import java.util.Map;

    public class HelpProcessor {
        public static void helpRequested(String[] command) {
            Map<String, String> commandDescriptions = new HashMap<>();
            commandDescriptions.put("CREATE", "Create a new database");
            commandDescriptions.put("SELECT", "Select data from the database");
            commandDescriptions.put("INSERT", "Insert data into the database");
            commandDescriptions.put("DELETE", "Delete data from the database");
            commandDescriptions.put("UPDATE", "Update data in the database");
            commandDescriptions.put("HELP", "Display help information");
            commandDescriptions.put("EXIT", "Exit the program");
            System.out.println(commandDescriptions);
            if (command.length == 1 && command[0].equalsIgnoreCase("HELP")) {
                for (Map.Entry<String, String> entry : commandDescriptions.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        }
    }