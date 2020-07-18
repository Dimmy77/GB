package ru.geakbrains.collection;


import java.util.*;

public class Main{

    public static void main(String[] args){
        List<String> list =new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        Iterator iterator = list.iterator(); //индекс элемента в объекте Collection
        list.remove("1");
        list.remove("2");

        list.add("5");
        iterator =list.iterator(); //требуется выполнять переопределение итератора каждый
        //раз после изменения объекта

        iterator.remove();
        if(iterator.hasNext()){
            iterator.next();
        }

        List<String> arrayList = new ArrayList<>(); //Массив объектов
        List<String> linkedList = new LinkedList<>(); //Массив ссылок на объекты. Быстрее
        //добавлять, удалять или расширять символы.

        Set<String> hashSet = new HashSet<>(); //Хранит элементы в произвольном порядке но в 1 экземпляре
        // (т.е. списки данных)
        Set<String> treeSet = new TreeSet<>(); //Отсортированные
        Set<String> linkSet = new LinkedHashSet<>(); //В порядке добавления элементов
    }
}
