package ru.geakbrains.level1.Lesson5;

public class Lesson4 {

    static final int SIZE = 10000000;
    static final int HALF=SIZE/2;


    public static void main(String[] arg) {
        System.out.println("Result of method #1");
        method1();
        System.out.println("\nResult of method #2 (from 1 to 5 thread)");
        for (int i = 1; i < 6; i++) {
            method2(i);
        }
    }

    public static void method1(){
//1) Создают одномерный длинный массив
        float[] arr = new float[SIZE];
        fillArray(arr,1);
        long startTime;
        startTime = currentTime();
        countArray(arr, 0);
//        5) Проверяется время окончания метода System.currentTimeMillis().
//        6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
        System.out.println("Time is: "+(currentTime()-startTime)+" (ms).");
    }

    public static void method2(int numberThred){
//1) Создают одномерный длинный массив
        float[] arr = new float[SIZE];
        fillArray(arr,1);
        long startTime;
        int partSize = SIZE/numberThred;
        startTime = currentTime();
        float[][] arr1=new float[numberThred][partSize+SIZE%numberThred];
        Thread t[] = new Thread[numberThred];
        int i;
        for (i = 0; i < numberThred; i++) {
            if(i == numberThred-1) {
                System.arraycopy(arr, i * partSize, arr1[i], 0, partSize+SIZE % numberThred);
            }
            else{
                System.arraycopy(arr, i * partSize, arr1[i], 0, partSize);
            }
            final int n=i;
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    countArray(arr1[n],n*partSize);
                }
            });
        }

        for (Thread a: t){ a.start();}

        for (Thread a: t){
            try {
                a.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        for (i = 0; i < numberThred; i++) {
            System.arraycopy(arr1[i],0,arr,i*partSize, arr1[i].length);
        }

//        5) Проверяется время окончания метода System.currentTimeMillis().
//        6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
        System.out.println("Number of thread is "+numberThred+" time is: "+(currentTime()-startTime)+" (ms).");
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
    public static void countArray(float[] arr, int number){
        for (int i =0;i<arr.length;i++) {
            arr[i]=(float)(arr[i]*Math.sin(0.2f+(i+number)/5)*Math.cos(0.2f+(i+number)/5)*Math.cos(0.4f+(i+number)/2));
        }
    }
}
