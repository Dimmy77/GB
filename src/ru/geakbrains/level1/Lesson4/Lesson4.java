package ru.geakbrains.level1.Lesson4;

public class Lesson4 {

    static final int SIZE = 10000000;
    static final int HALF=SIZE/2;


    public static void main(String[] arg) {
        System.out.println("Result of method #1");
        method1();
        System.out.println("\nResult of method #2");
        method2();
    }

    public static void method1(){
//1) Создают одномерный длинный массив
        float[] arr = new float[SIZE];
        fillArray(arr,1);
        long startTime;
        startTime = currentTime();
        countArray(arr);
//        5) Проверяется время окончания метода System.currentTimeMillis().
//        6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
        System.out.println("Time is: "+(currentTime()-startTime)+" (ms).");
    }

    public static void method2(){
//1) Создают одномерный длинный массив
        float[] arr = new float[SIZE];
        fillArray(arr,1);
        long startTime;
        startTime = currentTime();
        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];
        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);

        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                countArray(arr1);
            }
        });

        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {
                countArray(arr2);
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.arraycopy(arr1,0,arr,0, HALF);
        System.arraycopy(arr2,0,arr,HALF, HALF);

//        5) Проверяется время окончания метода System.currentTimeMillis().
//        6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
        System.out.println("Time is: "+(currentTime()-startTime)+" (ms).");
    }

//2) Заполняют этот массив единицами.
    public static void fillArray(float[] arr, float number){
        for (float a: arr) {
            a=number;
        }
    }

//3) Засекают время выполнения: long a = System.currentTimeMillis()
    public static long currentTime(){
        return System.currentTimeMillis();
    }

//4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
    public static void countArray(float[] arr){
        for (int i =0;i<arr.length;i++) {
            arr[i]=(float)(arr[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));
        }
    }
}
