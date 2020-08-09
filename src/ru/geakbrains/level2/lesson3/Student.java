package ru.geakbrains.level2.lesson3;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int score;
    Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    public void info(){
        System.out.println(name + " - "+ score);
    }
}
