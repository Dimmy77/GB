package ru.geakbrains.lessons;

//1.	Создать классы Собака и Кот с наследованием от класса Животное.
//3.	У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
public class Cat extends Animal {
    static int count = 0;

    public Cat(String name){
        super("Кошка", name);
        super.limitRun = 200;
        count++;
    }

    @Override
    public void swim(int distance) {
        System.out.println("Коты не умеют плавать");
    }

    @Override
    public void jump() {
        System.out.println("Кошка по имени "+super.name+ " прыгнула.");
    }
}
