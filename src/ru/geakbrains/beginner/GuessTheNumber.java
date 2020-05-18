package ru.geakbrains.beginner;

import java.util.Scanner;

public class GuessTheNumber {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Вам необходимо угадать число.");
        for (int i=10; i<=30; i+=10) {
            System.out.println("Уровень "+(i/10));
            Game_play(i);
        }
        System.out.println("Поздравляю. Вы выиграли!");
        scanner.close();
    }

    private static void Game_play(int range){
        int number = (int) (Math.random() * range);
        while (true) {
            System.out.println("Назовите число в диапазоне от 0 до " + range);
            int input_number = scanner.nextInt();
            if (input_number == number) {
                System.out.println("Вы угадали. Это действительно число " + number);
                break;
            }
            if (input_number > number) System.out.println("Загаданное число меньше названного вами");
            else System.out.println("Загаданное число больше названного вами");
        }
    }}
