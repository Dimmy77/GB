package ru.geakbrains.level0;

public class Plate {
    public int food;

    public Plate(int food){
        this.food = food;
    }

    public void info(){
        System.out.println("Plate: "+food);
    }

    //Lesson7 #4.	Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину
    // сыт (это сделано для упрощения логики программы).
    public boolean decreaseFood(int n){
        if(hasFood(n)){
            food -= n;
            return true;
        }
        System.out.println("В тарелке не достаточно еды.");
        return false;
    }

    //Lesson 7 №6.	Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
    public void addFood(int increment){
        food +=increment;
        System.out.println("В тарелку добавлено "+increment+" еды.");
    }

    //Lesson 7 #2.	Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды
    // (например, в миске 10 еды, а кот пытается покушать 15-20).
    private boolean hasFood(int n){
        return food-n>=0;
    }

    public void setFood(int food){
        this.food=food;
    }

    public int getFood(){
        return food;
    }
}
