package ru.geakbrains.lessons;

//1.	Создать классы Собака и Кот с наследованием от класса Животное.
//3.	У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
public class Dog extends Animal{
    static int count = 0;

    public Dog(String name){
        super("Собака", name);
        super.limitRun = 500;
        super.limitSwim = 10;
        count++;
    }

    @Override
    public void jump() {
        System.out.println("Собака по имени "+super.name+ " прыгнула.");
    }
}
