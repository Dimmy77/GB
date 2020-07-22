package ru.geakbrains.level2.lesson1;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.function.ObjLongConsumer;

public class Main {
    public static void main(String[] args){
        String[] arr = new String [] {"1", "2", "3", "a", "b"};
        System.out.print("Задание 1.\nИсходный массив: ");
        printArray(arr);
        changeTwoElement(arr, 3, 1);
        System.out.println("\nМассив после замены: ");
        printArray(arr);

        System.out.print("\n\nЗадание 2:\nИсходный массив:");
        printArray(arr);
        ArrayList<Object> arrayList = toArrayList(arr);
        System.out.println("\nArrayList: "+ arrayList);

        System.out.println("\n\n Задание 3:");
        ArrayList<Box> boxes = new ArrayList<>();

        Box<Apple> b1 = new Box<>(new Apple(), 10);
        Box<Apple> b2 = new Box<>(new Apple(), 10);
        Box<Orange> b3 = new Box<>(new Orange(), 5);

//        for (int i = 0; i < 5; i++) {
//            boxes.set(i, new Box<Apple>(new Apple()));
//            boxes.set(i+1, new Box<Orange>(new Orange()));
//        }
        b3.putIntoBox(new Apple(), 5);
        b3.putIntoBox(new Orange(), 5);

        System.out.println("Вес коробки №1 =" + b1.getWeight());
        System.out.println("Вес коробки №2 =" + b2.getWeight());
        System.out.println("Вес коробки №3 =" + b3.getWeight());

        System.out.println("Вес коробок 1 и 2:"+ b1.compare(b2));
        System.out.println("Вес коробок 1 и 3:"+ b1.compare(b3));

        b1.putToAnotherBox(b2,3);
        b2.putToAnotherBox(b3,3);

        System.out.println("Вес коробки №1 =" + b1.getWeight());
        System.out.println("Вес коробки №2 =" + b2.getWeight());
        System.out.println("Вес коробки №3 =" + b3.getWeight());

    }

//    1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
    public static void changeTwoElement(Object[] arr, int firstElement, int secondElement){
        if(firstElement >= arr.length || secondElement >= arr.length){
            System.out.println("Индекс элемента превышает длину массива");
            return;
        }
        Object tmp = arr[firstElement];
        arr[firstElement] =arr[secondElement];
        arr[secondElement] = tmp;
    }

    //2. Написать метод, который преобразует массив в ArrayList;
    public static ArrayList toArrayList(Object[] arr){
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Object a: arr) {
            arrayList.add(a);
        }
        return arrayList;
    }

    public static void printArray(Object[] arr){
        for (Object a: arr) {
            System.out.print(a+ ", ");
        }
    }

}
