package ru.geakbrains.level2.lesson1;

class Fruit{
    public float weight = 0.0f;
    public String name;

    public Fruit(){
        name = "fruit";
    }
    public float getWeight() {
        return weight;
    }

    public String getName(){
        return name;
    }
}

class Apple extends Fruit{
    Apple() {
        name = "apple";
        weight = 1.0f;
    }
}

class Orange extends Fruit{
    Orange(){
        name = "orange";
        weight = 1.5f;
    }
}

