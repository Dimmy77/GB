package ru.geakbrains.level2.lesson6;

//3. *Добавить на серверную сторону сетевого чата логирование событий.

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import java.io.IOException;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger("");
    private static int[] a;

    public static void main (String[] args){
        logger.setLevel(Level.ALL);
        Handler handler = null;

        try {
            handler = new FileHandler("mylog1.txt");
            handler.setLevel(Level.ALL);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.getHandlers()[0].setLevel(Level.ALL);
        logger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel()+"\t" + record.getMessage() + "\n";
            }
        });

        int[] enter;
        for (int i = 0; i < (int) (Math.random()*10); i++){
            int len =(int) (Math.random()*100);
            boolean has4 = false;
            int last4 = -1;
            enter = new int[len];
            for (int j=0; j < len; j++){
                enter[j] = (int) (Math.random()*20);
                if(enter[j] == 4) {
                    logger.log(Level.INFO, "Test #"+ i+"has 4 at position "+ j);
                    has4 = true;
                    last4 = j;
                }
            }
            if (has4) logger.log(Level.WARNING, "Test #"+ i+"has last 4 at position "+ last4);
            else logger.log(Level.WARNING, "Test #"+ i+"dosn't have 4");

            try{
                Main mn = new Main(enter);
                int b[] = arrayAfter4();
                for (int f: b) {
                    System.out.print (f+ "; ");
                }
                System.out.println("\n");
            }catch (RuntimeException e){
                e.printStackTrace();
                System.out.println("Dosn't have 4.");
            }
        }

        logger.removeHandler(handler);

        Handler handler1;

        try {
            handler1 = new FileHandler("mylog2.txt");
            handler1.setLevel(Level.ALL);
            handler1.setFormatter(new SimpleFormatter());
            logger.addHandler(handler1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.getHandlers()[0].setLevel(Level.ALL);
        logger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel()+"\t" + record.getMessage() + "\n";
            }
        });


        for (int i = 0; i < (int) (Math.random()*10); i++) {
            int len = (int) (Math.random() * 100);
            boolean has4or1 = false;
            enter = new int[len];
            for (int j = 0; j < len; j++) {
                enter[j] = (int) (Math.random() * 20);
                if (enter[j] == 4 || enter[j] == 1) {
                    logger.log(Level.INFO, "Test #" + i + "has 4 or 1 at position " + j);
                    has4or1 = true;
                }
            }
            if (!has4or1) logger.log(Level.WARNING, "Test #" + i + "dosn't have 4 or 1");
            Main mn = new Main(enter);
            System.out.println("\n Result 2: "+ hasOneOrFore());
        }

    }

    Main(int... a){
        this.a = a;
    }

//1. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
// Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после
// последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить
// RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных).
// Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
    public static int[] arrayAfter4(){
        boolean isResult = false;
        int i;
        for (i = a.length-1; i>=0;i--){
            if (a[i] == 4){
                isResult = true;
                i++;
                break;
            }
        }
        if(isResult){
            int [] b = new int [a.length-i];
            for (int j = 0; j<b.length; j++){
                b[j] = a[i+j];
            }
            return b;
        }
        else{
           throw new RuntimeException("Don't have 4 in array");
        }
    }

//2. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
// то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).

    public static boolean hasOneOrFore(){
        for (int i: a) {
            if(i == 1 || i ==4) return true;
        }
        return false;
    }

}
