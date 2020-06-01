package ru.geakbrains.lessons_0;

// 1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
public class Employee {
    private String family;
    private String name;
    private String secondName;
    private String position;
    private String email;
    private String phone;
    private float salary;
    private int age;

    //2. Конструктор класса должен заполнять эти поля при создании объекта
    public Employee(String family,
                  String name,
                  String secondName,
                  String position,
                  String email,
                  String phone,
                  float salary,
                  int age){
        this.family = family;
        this.name = name;
        this.secondName = secondName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }
    //3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль
    public void printConsole(){
        System.out.println(family+"   "+
                name+"   "+
                secondName+"    "+
                position+"   "+
                email+"   "+
                phone+"   "+
                salary+"   "+
                age);
    }
}
