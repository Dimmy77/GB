package ru.geakbrains.level2.lesson7;

//1. Создать класс, который может выполнять «тесты», в качестве тестов выступают классы с наборами методов с
// аннотациями @Test. Для этого у него должен быть статический метод start(), которому в качестве параметра передается
// или объект типа Class, или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite,
// если такой имеется, далее запущены методы с аннотациями @Test, а по завершению всех тестов – метод с аннотацией
// @AfterSuite.
//
// К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10), в соответствии с которыми
// будет выбираться порядок их выполнения, если приоритет одинаковый, то порядок не имеет значения. Методы с
// аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре, иначе необходимо бросить
// RuntimeException при запуске «тестирования».
//Это домашнее задание никак не связано с темой тестирования через JUnit и использованием этой библиотеки, то есть
// проект пишется с нуля.
//
//2. Написать программу для проверки ДЗ
//(Проанализировать папку с компилированными классами и вызвать методы, проверить результат)

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class NewTest {

    public static void main (String[] args){
        test(IntTest.class);

    }

    public static void test(Class c){
        List<Method> methodList = new ArrayList<>();

        for(Method m: c.getDeclaredMethods()) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                methodList.add(m);

            }
        }

        for(Method m: c.getDeclaredMethods()){
            if (m.isAnnotationPresent(Test.class)) {
                if(m.getAnnotation(Test.class).priority() > 10 || m.getAnnotation(Test.class).priority() < 0) throw new RuntimeException("Priority exeption");
                methodList.add(m);
                System.out.println("1");
            }

            methodList.sort((o1,o2)-> {return o1.getAnnotation(Test.class).priority()-o2.getAnnotation(Test.class).priority();});

            for(Method ma: c.getDeclaredMethods()) {
                if (ma.isAnnotationPresent(AfterSuite.class)) {
                    methodList.add(ma);
                }
            }

            for (Method o: methodList) {
                try {
                    o.invoke(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


