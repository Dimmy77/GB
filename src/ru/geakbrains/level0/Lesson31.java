package ru.geakbrains.level0;

import java.util.Scanner;

/*Написать программу, которая загадывает случайное число от 0 до 9
и пользователю дается 3 попытки угадать это число.
При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное,
или меньше. После победы или поигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»
(1 – повторить, 0 – нет).*/

public class Lesson31 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Здесь можно предоставить выбор между 2 играми.
        short repeat=3;
        do{
            System.out.println("\nВам необходимо угадать число с "+ repeat+" попыток.");
            newGame(9, repeat);
         }while (repeatGame());
        scanner.close();
    }

    private static void newGame(int range, short repeat){
        //Random r=new Random();
        //int number = r.nextInt();
        int number = (int) (Math.random() * range);
        for (int i=0; i<repeat; i++) {
            System.out.println("Попытка № "+(i+1)+".");
            System.out.println("Назовите число в диапазоне от 0 до " + range);
            int input_number = scanner.nextInt();

            if (input_number == number) {
                System.out.println("Вы угадали. Это действительно число " + number);
                break;
            }
            if(i==repeat-1){
                System.out.println("Вы проиграли. Загаданное число " + number);
                break;
            }
            if (input_number > number) System.out.println("Загаданное число меньше названного вами");
            else System.out.println("Загаданное число больше названного вами.");
        }
    }

    private static int getNumbetFromConcole(){
        Scanner sc=new Scanner(System.in);
        do{
            if (sc.hasNextInt()){
                return sc.nextInt();
            }
            System.out.println("Введите целое число");
            sc.nextLine();
        }while (true);
    }
    private static boolean repeatGame(){
        System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
        while (true){
            int gameOver = scanner.nextInt();
            if(gameOver==0 || gameOver==1){
                if(gameOver==1) return true;
                else return false;
            }
            System.out.println("Введите число в диапазоне от 0 до 1");
         }
    }
}
