package ru.oop.task.service.commands;

import ru.oop.task.model.Pet;
import ru.oop.task.repo.PetRepository;
import ru.oop.task.service.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class DeleteProcessor {
    public  static String deleteCommand (String[] words){
    if (DatabaseService.hasDb(words[2])){
        if (!words[1].equalsIgnoreCase("FROM") || !words[3].equalsIgnoreCase("WHERE")){
            return """
                        Неправильно оформлена команда DELETE.
                                       
                        Корректный ввод -> DELETE FROM {Название базы данных} WHERE {условие}
                                           
                        """;
        }
        else {
            return  createDeleteOutput(words);
        }
    }
    }
    public static String createDeleteOutput (String[] words){
        List <String> outputList = new ArrayList<>();
        if ( words.length == 4 && words[1].equalsIgnoreCase("FROM")) {
                if (words[2].equalsIgnoreCase("pets")){
                    PetRepository repository = new PetRepository();
                    for (Pet )
                    outputList.add("""
                            Delete from %s. Deleted values is (id=%d, name="%s", breed="%s", age=%d).
                            """.formatted(words[2], ));
                }
        }
        }
}
