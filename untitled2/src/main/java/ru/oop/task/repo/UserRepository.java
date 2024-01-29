package ru.oop.task.repo;

import ru.oop.task.model.Database;
import ru.oop.task.model.User;

public class UserRepository extends MainRepository<User> {
    public UserRepository() {
        super(new Database<>());
    }
}