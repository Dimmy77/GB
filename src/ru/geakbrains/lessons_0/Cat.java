package ru.geakbrains.lessons_0;

//Lesson6 #1.	Создать классы Собака и Кот с наследованием от класса Животное.
//Lesson6 #3.	У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
public class Cat extends Animal {
    //Lesson7 #3.	Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны).
    // Если коту удалось покушать (хватило еды), сытость = true.
    static int count = 0;
    int appetite;
    boolean isHangry;
    double startTime;
    final int HANGRY_TIME=100000;

    public Cat(String name){
        super("Кошка", name);
        super.limitRun = 200;
        count++;
        isHangry = false;
    }

    public Cat(String name, int appetite){
        super("Кошка", name);
        super.limitRun = 200;
        count++;
        this.appetite=appetite;
        isHangry = false;
    }

    public void setAppetite(int appetite){
        this.appetite=appetite;
    }

    public int getAppetite(){
        return appetite;
    }

    public void eatFrom(Plate plate){
        //Lesson7 #3. Если коту удалось покушать (хватило еды), сытость = true.
        if(isHangry()) {
            isHangry = plate.decreaseFood(appetite);
        }
        else{
            System.out.println(name+"еще сыт.");
        }
    }

    public boolean isHangry(){
        if(System.nanoTime()-startTime>=HANGRY_TIME) {
            startTime=System.nanoTime();
            return true;
        }
        return false;
    }

    public void printHangry(){
        if (isHangry) System.out.println("Кошка по имени "+name+ " сыта.");
        else System.out.println("Кошка по имени "+name+ " голодна.");
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
