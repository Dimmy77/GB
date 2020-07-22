package ru.geakbrains.level2.lesson1;


//3. Большая задача:
//        a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
//        b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
//        поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
//        c. Для хранения фруктов внутри коробки можете использовать ArrayList;
//        d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного
//        фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
//        e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
//        которую подадут в compare в качестве параметра, true - если их веса равны,
//        false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
//        f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую
//        коробку(помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
//        соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты,
//        которые были в этой коробке;
//        g. Не забываем про метод добавления фрукта в коробку.

public class Box<T extends Fruit> {
    private T obj;
    private int boxQty;


    public Box(T obj) {
        this.obj = obj;
    }

    public Box(T obj, int qty) {
        this.obj = obj;
        boxQty = qty;
    }

    public void putIntoBox(Fruit a, int qty) {
        if(qty < 0){
            System.out.println("Нельзя положить отрицательное число фруктов");
            return;
        }
        if(obj.getName() == a.getName()){
            this.boxQty+=qty;
        }else{
            System.out.println("Данная коробка для " + obj.getName());
        }
    }

    public void getFromBox (Fruit a, int qty) {
        if(qty < 0){
            System.out.println("Нельзя взять отрицательное число фруктов");
            return;
        }
        if(obj.getName() == a.getName()){
            if(this.boxQty>=qty){
                this.boxQty-=boxQty;
            }
            else{
                System.out.println("В данной коробке нет "+ qty+" "+ obj.getName());
            }

        }else{
            System.out.println("В этой коробке храняться " + obj.getName());
        }
    }

    public float getWeight(){
        return boxQty * obj.getWeight();
    }

    public boolean compare(Box a) {
        if (obj.getName().equals(a.typeFruit()) && boxQty == a.getWeight()) {
            return true;
        }
        return false;
    }

    public void putToAnotherBox(Box a, int qty){
        if (obj.getName() == a.typeFruit() && qty <= boxQty){
            this.getFromBox(obj, qty);
            a.putIntoBox(obj, qty);
        }
        else {
            System.out.println("Нельзя положить "+ qty+" "+ obj.getName() + " в ящик с "+ a.boxQty+ " "+a.typeFruit());
        }
    }

    public String typeFruit(){
        return obj.getName();
    }
}

