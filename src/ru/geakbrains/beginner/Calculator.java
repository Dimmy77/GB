package ru.geakbrains.beginner;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int op=-1, a=0, b=0, result = 0;
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

            if (op == 1) result = a + b;
            if (op == 2) result = a - b;
            if (op == 3) result = a * b;
            if (op == 4) result = a / b;
            if (op >= 0 & op <= 4) System.out.println("Результат: " + result);
            else System.out.println("Операция введена не корректно");
        } while (op != 0);
        scanner.close();
    }
}
