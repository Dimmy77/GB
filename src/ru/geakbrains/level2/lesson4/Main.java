package ru.geakbrains.level2.lesson4;

public class Main {
    public static void main(String[] args){
        Print a = new Print(5);
        Thread t1 = new Thread(()->{
           a.printA();
        });
        Thread t2 = new Thread(()->{
            a.printB();
        });
        Thread t3 = new Thread(()->{
            a.printC();
        });
        t1.start();
        t2.start();
        t3.start();
    }


}
//1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
// Используйте wait/notify/notifyAll.
class Print {
    private final Object mon = new Object();
    private volatile char letter = 'A';
    int time;

    Print(int time){
        this.time = time;
    }

   public void printA() {
        synchronized (mon){
            try{
                for (int i = 0; i < time; i++) {
                    while (letter != 'A') {
                        mon.wait();
                    }
                    System.out.print("A");
                    letter = 'B';
                    mon.notify();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon){
            try{
                for (int i = 0; i < time; i++) {
                    while (letter != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    letter = 'C';
                    mon.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (mon){
            try{
                for (int i = 0; i < time; i++) {
                    while (letter != 'C') {
                        mon.wait();
                    }
                    System.out.print("C");
                    letter = 'A';
                    mon.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
