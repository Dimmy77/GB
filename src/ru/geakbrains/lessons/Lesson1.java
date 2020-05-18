package ru.geakbrains.lessons;

import java.util.Scanner;

public class Lesson1 {
    public static void main (String[] args){
        //Задание 2: Создать переменные всех пройденных типов данных и инициализировать их значения
        byte b1=2;
        short s1=-356;
        int i=2020;
        long l=i*s1;
        float a = 1.0f, b=10.232f, c=21, d=31;
        double dd=calculate(a, b, c, d);
        boolean bool1=true;
        char c1='D';
        String Word = "Даша";


        System.out.println("Результат задания 3: "+dd);
        System.out.println("\nРезультат задания 4: "+isLimit(12,5));
        System.out.println("\nРезультат задания 5: \n Введенное число: "+s1);
        printMinus((int)s1);
        System.out.println("\nРезультат задания 6:\n Введенное число: " +(-b1) +"\n Результат:"+isMunus1((int)-b1));
        System.out.println("\nРезультат задания 7: ");
        printHello(Word);

        System.out.println("\nРезультат задания 8: ");
        System.out.println("Введите год: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Год высокосный? - "+whatsYear(scanner.nextInt()));
        scanner.close();
    }

    //Задание 3: Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    //где a, b, c, d – аргументы этого метода, имеющие тип floa
    private static float calculate (float a, float b, float c, float d){
        return a*(b+(c/d));
    }

    //Задание 4: Написать метод, принимающий на вход два целых числа и проверяющий,
    // что их сумма лежит в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.
    private static boolean isLimit(int a, int b){
        int c=a+b;
        return c>=10 && c<=20;
        //if (c>=10 && c<=20) return true;
        //else return false;
    }

    //Задание 5: Написать метод, которому в качестве параметра передается целое число,
    // метод должен напечатать в консоль, положительное ли число передали или отрицательное.
    // Замечание: ноль считаем положительным числом
    private static void printMinus (int a){
        if(a>=0) System.out.println("Число положительное");
        else System.out.println("Число отрицательное");
    }

    //Задание 6: Написать метод, которому в качестве параметра передается целое число.
    // Метод должен вернуть true, если число отрицательное
    private static boolean isMunus1(int a){
        return a<0;
        //if(a<0) return true;
        //return false;
    }

    //Задание 7: Написать метод, которому в качестве параметра передается строка, обозначающая имя.
    // Метод должен вывести в консоль сообщение «Привет, указанное_имя!».
    private static void printHello(String a){
        System.out.println("Привет, " +a);
    }

    //Задание 8: *Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    private static boolean whatsYear(int Year){
        if (Year%4!=0) return false;
        else if (Year%100!=0 ^ Year%400==0) return true;
        return false;

        // return (Year%4 == 0 && Year%100!=0 || Year%400==0);
    }
}
