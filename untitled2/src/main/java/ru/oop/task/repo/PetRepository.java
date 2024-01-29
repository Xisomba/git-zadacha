package ru.oop.task.repo;

import ru.oop.task.model.Database;
import ru.oop.task.model.Pet;

public class PetRepository extends MainRepository<Pet> {
    public PetRepository() {
        super(new Database<>());
    }
}