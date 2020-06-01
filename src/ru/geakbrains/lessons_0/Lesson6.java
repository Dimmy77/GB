package ru.geakbrains.lessons_0;

import java.util.Arrays;

//1.	Создать классы Собака и Кот с наследованием от класса Животное.
//2.	Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
// Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
//3.	У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
//4.	* Добавить подсчет созданных котов, собак и животных.

public class Lesson6 {
    public static void main(String[] args) {
        String[] name = {"Барсик", "Тузик", "Мухтар", "Шавка", "Люцефер", "Когтяузер", "Джери", "Буренка", "Васька",
                "Тошка", "Баллу", "Манчес", "Буйволсон", "Ник", "Джуди", "Барашкис", "Златогрив"};
        Animal[] animal= new Animal [10];
        Cat[] cats = new Cat[7];
        Dog[] dogs = new Dog[4];

        for(int i=0;i<10;i++){
            System.out.println();
            animal[i] = new Animal("",name[(int)(Math.random()*name.length)]) {
                @Override
                public void jump() {

                }
            };

            if (i < cats.length){
                cats[i] = new Cat(name[(int)(Math.random()*name.length)]);
            }
            if(i < dogs.length){
                dogs[i] = new Dog(name[(int)(Math.random()*name.length)]);
            }
        }



//4.	* Добавить подсчет созданных котов, собак и животных. (Вариант с использование static в классе)
        System.out.println("Вариант с расчетом с использованием static в классе:");
        System.out.println("Количество животных: "+animal[0].count);
        System.out.println("Количество кошек: "+cats[0].count);
        System.out.println("Количество собак: "+dogs[0].count);


        //4.	* Добавить подсчет созданных котов, собак и животных. (Вариант с использование StreamAPI)
        int fauna = animal.length+cats.length+dogs.length;
        int j=0;
        Animal[] zoo = new Animal[fauna];
        for (int i=0; i<10;i++){
            zoo[j]=animal[i];
            zoo[j].type="Животное";
            j++;
            if (i < cats.length){
                zoo[j]=cats[i];
                j++;
            }
            if(i < dogs.length){
                zoo[j]=dogs[i];
                j++;
            }

        }

/*        int index;
        int action;
        int distance;
        for(int i=0;i<fauna*5;i++){
            index = (int)(Math.random()*(fauna-1));
            action=(int)(Math.random()*3);
            distance=(int)(Math.random()*550);
            System.out.println("Животное №:"+index+" Действие:"+action+" Расстояние:"+distance);
            System.out.print(i+" ");

            switch (action){
                case 0:
                    zoo[index].run(distance);
                    break;
                case 1:
                    //if (zoo[index] instanceof Cat){
                    //    Cat tmp = (Cat) zoo[i];
                    //    tmp.swim(5);
                    //}
                    //else{
                    zoo[index].swim(distance);
                    //}
                    break;
                case 2:
                    zoo[index].jump();
            }
        }*/

        System.out.println("\n\n\nВариант с расчетом в Stream API:");
        System.out.println("Количество животных: "+Arrays.stream(zoo).count());
        System.out.println("Количество кошек: "+Arrays.stream(zoo).
                filter(t->t.type.equals("Кошка")).
                count());
        System.out.println("Количество собак: "+Arrays.stream(zoo).
                filter(t->t.type.equals("Собака")).
                count());
    }
}


