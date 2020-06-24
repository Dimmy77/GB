package ru.geakbrains.level0;

import java.util.Arrays;

public class Lesson7 {
    public static void  main(String[] args) {
        //Lesson7 #1.	Расширить задачу про котов и тарелки с едой.
        String[] name = {"Барсик", "Тузик", "Мухтар", "Шавка", "Люцефер", "Когтяузер", "Джери", "Буренка", "Васька",
                "Тошка", "Баллу", "Манчес", "Буйволсон", "Ник", "Джуди", "Барашкис", "Златогрив"};
        Cat[] cat = new Cat[name.length];
        Plate[] plate = new Plate[5];

        //5.	Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести
        // информацию о сытости котов в консоль.
        for (int i=0;i< name.length;i++){
            cat[i] = new Cat(name[(int)(Math.random()*name.length)],(int)(Math.random()*20));
            if(i < plate.length) plate[i] = new Plate((int)(50+Math.random()*100));
        }

        System.out.println("Имеющиеся кошки и тарелки:");
        printCat(cat);
        Arrays.stream(plate).forEach(p->p.info());

        int numberCat;
        int numberPlate;

        System.out.println("\n\n Кошки пошли кушать:");

        //Кормим кошек в произвольном порядке и из произвольных тарелок. Количество итераций делаем равной количеству
        // кошек * 3.
        for(int i=0; i <= cat.length*3; i++) {
            numberCat = (int)(Math.random()*cat.length);
            numberPlate = (int)(Math.random()*plate.length);
            cat[numberCat].eatFrom(plate[numberPlate]);
            //Если еды в тарелке осталось меньше чем ест текущая кошка, тогда добавляем произвольное кол-во корма
            // в тарелку, в зависимости от аппетита кота.
            if (plate[numberPlate].getFood() < cat[numberCat].getAppetite()){
                plate[numberPlate].addFood((int)(Math.random()*cat[numberCat].getAppetite()*2));
            }

            //Даем коту проголодаться
            try {
                Thread.sleep(100);
            } catch(InterruptedException ex) {
                System.out.println("ВНИМАНИЕ: Слишком долго ждем!");
            }

        }

        //Cat.fff();

       System.out.println("\n\nКотики поели:");
        printCat(cat);
        Arrays.stream(plate).forEach(p->p.info());
    }

    private static void printCat(Cat[] a){
        for(int i=0;i <a.length;i++){
            a[i].isHangry();
            a[i].printHangry();
        }
    }
}
