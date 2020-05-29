/*
package ru.geakbrains.streamAPI;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {
    private static class Person{
        private String name;
        private String position;
        private int age;

        public Person(String name, String position, int age){
            this.name = name;
            this.position=position;
            this.age=age;
        }
    }

    public static void  main(String[] args){
        List<Person> person = new ArrayList<>(Arrays.asList(
                new Person("Bob1", "Engeneer", 25),
                new Person("Bob2", "Engeneer", 34),
                new Person("Bob3", "Manager", 27),
                new Person("Bob4", "Engeneer", 45),
                new Person("Bob5", "Cheef", 39),
                new Person("Bob6", "Engeneer", 21)
        ));

        List<Person> engeeners = new ArrayList<>();

        for (Person p:person){
            if(p.position.equals("Engeneer")){
                engeeners.add(p);
            }
        };
        Collections.sort(engeeners, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.age-o1.age;
            }
        });

        List<String> engeneersNames = new ArrayList<>();
        for (Person p:engeeners){
            engeneersNames.add(p.name);
        }
        System.out.println(engeneersNames);

//или то же самое...
        List<String> engNames = person.stream()
                .filter(p->p.position.equals("Engeneer"))
                .sorted((o1,o2)-> o1.age-o2.age)
                .map(p->p.name)
                .collect(Collectors.toList());
        //.forEach(System.out::println);
        System.out.println(engNames);
//Пример
        List <Integer> list = new ArrayList<>(Array(1,2,3,4,5));
        List.stream().map(n -> n * 5).forEach(System.out::println);
        list.stream().map(z->{
            StringBuffer builder = new StringBuffer();
            for(int i=0; i<z;i++){
                builder.append("A");
            }
        }).forEach(str->System.out.println(str));
        System.out.println(list.stream().filter(z->z<6).count());
        list.stream().sorted().limit(3).forEach(System.out::println); //убрать первые 3

        list.stream().allMatch(new Predicate<Integer>() { //возвращает boolean что ВСЕ элементы. anyMatch, что какой то 1 элемента,
            @Override                                       //nonMatch - ни один элемент
            public boolean test(Integer integer) {
                return integer>0;
            }
        });
        //тоже самое
        System.out.println(list.stream().allMatch(n ->n>0));

        //Option -контейнер. Используется для обработки исключений. Например если в результате поиска возвращается null
        Optional<Integer> opt = list.stream().filter(n->n <3).findFirst();
        opt.ifPresent(i->System.out.println(opt.get()));
        opt.orElseThrow(()-> new RuntimeException("Something wrong"));

//Еще примеры
        Runnable obj = new Runnable() {
            @Override
            public void run() {

            }
        };
//         public class MainApp$1 implements Runnable{
//             @Override
//             public void run(){
//
//
//             }
//         }
        System.out.println(obj.getClass().getName());

        //Лямбда выражения
        //Вызов метода интерфейса может быть выполненен следующим образом
        new Thread(new Runnable() {
            {
                @Override
                public void run() {

            }
            }
        }).start();
        //или используя лямбда выражения, таким образом
        new Thread( () -> {}).start();

        Runnable r1=()->{
            System.out.println(1);
        };
        Runnable r2=()->{
            System.out.println(2);
        };

        new Thread(r1);
        new Thread(r2);
        //позволяет либо сохранить результат вызова метода в переменную или передачи метода в метод

        //Коллекторы

        //Создание стрима
        //Коллекции
        List<String> strList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        //Массивы
        String[] arr = {"A", "B", "C"};
        Stream<String> arrStream = Arrays.stream(arr);
        //Создание на ходу
        Stream<String> streamBuilder = Stream.of("A", "B", "C");
    }
}
*/
