package ru.oop.task.model;

import java.util.Map;

public class User {
    private String firstName;
    private String secondName;
    private int age;
    private Integer id;
    private Integer petId;

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.secondName = lastName;
        this.age = age;
    }
public  static String getValueByFieldName (String fieldName){

}
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

}

