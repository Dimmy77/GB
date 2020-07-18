package ru.geakbrains.level0;
/*2	* Создать массив из слов
String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь.
Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
apple – загаданное
apricot - ответ игрока
ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
Для сравнения двух слов посимвольно можно пользоваться:
String str = "apple";
char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
Играем до тех пор, пока игрок не отгадает слово.
Используем только маленькие буквы.*/

import java.util.Arrays;
import java.util.Scanner;

public class Lesson32 {
    static public void main(String[] arg){
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        int number = (int) (Math.random()*words.length);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вам необходимо угадать загаданное слово:"+Arrays.toString(words));
//        System.out.println("Я загадал:"+words[number]);
        do {
            System.out.println("\nВведите ваше слово:");
            String userWords = scanner.next();
            if (userWords.equals(words[number])) {
                System.out.println("Вы угадали. Это слово действительно: " + userWords);
                break;
            } else {
                printHelp(words[number],userWords);
            }
        }while(true);
        scanner.close();
    }

    static private void printHelp(String word, String word2){
        int min=(word.length()<word2.length())?word.length():word2.length();
        int i;
        for (i=0; i<min;i++){
            if(word.charAt(i) == word2.charAt(i)){
                System.out.print(word.charAt(i));
            }
            else{
                System.out.print("#");
            }
        }
        while(i<15){
            System.out.print("#");
            i++;
        }
    }
}
