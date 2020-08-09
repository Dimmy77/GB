package ru.geakbrains.level2.lesson5;

import java.util.Scanner;

//Создать консольный калькулятор с использованием лямбды (у калькулятора должно быть 4 функции: сложение, вычитание,
// умножение, деления, достаточно сделать реализацию для двух чисел в одной операции)

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int op=-1, a=0, b=0;
        float result = 0;
        Sum sum;
        sum = (x, y) -> x+y;

        Minus minus;
        minus = (x, y) -> x-y;

        Mult mult;
        mult = (x, y) -> x*y;

        Dev dev;
        dev = (x, y) -> x/y;

        do {
            System.out.println("Выберите операцию:");
            System.out.println("1. Сложение");
            System.out.println("2. Вычитание");
            System.out.println("3. Умножение");
            System.out.println("4. Деление");
            System.out.println("Введите 0 (ноль) для выхода.");
            op=scanner.nextInt();
            if(op == 0) break;

            System.out.println("Введите 1 число:");
            a = scanner.nextInt();

            System.out.println("Введите 2 число:");
            b = scanner.nextInt();

            if (op == 1) result = sum.sum(a, b);
            if (op == 2) result = minus.minus(a,b);
            if (op == 3) result = mult.mult(a, b);
            if (op == 4) result = dev.dev(a, b);
            if (op >= 0 & op <= 4) System.out.println("Результат: " + result);
            else System.out.println("Операция введена не корректно");
        } while (op != 0);
        scanner.close();
    }
}

interface Sum{
    public int sum(int a, int b);
}

interface Dev{
    public float dev(int a, int b);
}

interface Minus{
    public int minus(int a, int b);
}

interface Mult{
    public int mult(int a, int b);
}
