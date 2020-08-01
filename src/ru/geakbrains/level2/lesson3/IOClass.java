package ru.geakbrains.level2.lesson3;

//4.   Сделать клиен-серверное приложение. Передать по сети сеарилизованный объект.

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class IOClass {

    public static void main(String[] args){
        //Задание 1
        readFile("out/production/GB/ru/geakbrains/level2/lesson3/test1.txt");

        //Задание 2 - Результат выполнения записывается в файл: "out/production/GB/ru/geakbrains/level2/lesson3/test2"
        try {
            unionFiles("out/production/GB/ru/geakbrains/level2/lesson3/test2-1",
                    "out/production/GB/ru/geakbrains/level2/lesson3/test2-2",
                    "out/production/GB/ru/geakbrains/level2/lesson3/test2-3",
                    "out/production/GB/ru/geakbrains/level2/lesson3/test2-4",
                    "out/production/GB/ru/geakbrains/level2/lesson3/test2-5");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Задание 3.
        readBook("out/production/GB/ru/geakbrains/level2/lesson3/ThirdPartyNotices.txt");



    }

    //1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
    public static void readFile(String file){
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] arr = new byte[512];
            int x;
            while ((x = in.read(arr)) != -1) {
                System.out.print(new String(arr, 0, x));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция:
// ArrayList<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);

    public static void unionFiles(String ... files) throws IOException {
        ArrayList<InputStream> ali = new ArrayList<>();
        try {
            for (int i = 0; i < files.length; i++) {
                ali.add(new FileInputStream(files[i]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));

        File file = new File ("out/production/GB/ru/geakbrains/level2/lesson3/test2");
        if(file.exists()) {
            file.delete();
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        int x;
        while (true) {
            if (!((x = in.read()) != -1)) break;
            fw.write(x);
        }
        fw.close();
        in.close();
    }

//3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
// Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
// Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
    public static void readBook(String file){
        final int PAGE_SIZE = 1800;
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            Scanner sc = new Scanner(System.in);
            int p = 1;

            while (true){
                System.out.println("\nВведите страницу (для выхода 0): ");
                p = sc.nextInt() - 1;
                if (p < -1) {
                    System.out.println("Число страниц должно быть положительным.");
                }
                else if(p == -1){ break;}
                else{
                    raf.seek(p * PAGE_SIZE);
                    byte[] arr = new byte[1800];
                    raf.read(arr);
                    System.out.println(new String(arr));
                }
            }
            raf.close();
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
