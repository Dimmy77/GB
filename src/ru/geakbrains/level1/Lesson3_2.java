package ru.geakbrains.level1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


/*1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов,
из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
*/
public class Lesson3_2 {

    public static void main(String args[]) {
        //Определяем словарь слов
        String[] dictionary = {"Барсик", "Тузик", "Мухтар", "Шавка", "Люцефер", "Когтяузер", "Джери", "Буренка", "Васька",
                "Тошка", "Баллу", "Манчес", "Буйволсон", "Ник", "Джуди", "Барашкис", "Златогрив"};
        //Создаем массив
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arrayList.add(dictionary[(int) (Math.random() * dictionary.length)]);
        }

        HashMap<String, Integer> hashMap = new HashMap<>();

//Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
// Посчитать сколько раз встречается каждое слово.
        for (int i = 0; i < arrayList.size(); i++) {
            Integer qty = hashMap.get(arrayList.get(i));
            hashMap.put(arrayList.get(i), qty == null ? 1 : qty + 1);
        }

        System.out.print("Список уникальных слов в массиве: {");
        hashMap.forEach((s, integer) -> {
            System.out.print(s + ", ");
        });
//Если нужно сохранить в отдельный массив, тогда можно вместо вывода на печать 's' записать в новый массив.
        System.out.print("}\n");

        System.out.print("\n Количество повторяющихся слов: ");
        System.out.println(hashMap);

        Employee emp = new Employee();
        for (int i = 0; i < 30; i++) {
            ArrayList<String> tmp =new ArrayList<>();
            // Генерация телефонных номеров
            for (int j=0;j <(int)(Math.random()*4+1);j++){
                tmp.add("+" + (int) (Math.random() * 10) + "(" + (int) (Math.random() * 1000) + ")" + (int) (Math.random() * 1000) + "-" + (int) (Math.random() * 10000));
            }
            emp.add(dictionary[(int)(Math.random()*dictionary.length)], tmp);
        }
        System.out.println("\n********************Телефонный справочник******************");
        emp.printList();
        System.out.println("\nТелефон "+dictionary[5]+": "+emp.get(dictionary[5]));
    }
}

//2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
//В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер
//телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов, тогда при запросе такой
//фамилии должны выводиться все телефоны.
//
//Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще
//дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.). Консоль
//желательно не использовать (в том числе Scanner), тестировать просто из метода main() прописывая add() и get().

class Employee {
    private HashMap<String, ArrayList> employee = new HashMap<>();

    public  Employee(){

    }

    public Employee(String family, ArrayList phone) {
        employee.put(family,phone);
    }

    public Employee(HashMap employee) {
        this.employee=employee;
    }

    public void add(String family, ArrayList phone){
        employee.put(family, phone);
    }

    public ArrayList get(String family){
        return employee.get(family);
    }

    public void printList(){
        System.out.println(employee);
    }

//    @Override
//    public String toString() {
//        return String.valueOf(salary);
//    }
//
//    @Override
//    public int hashCode() {
//        return salary;
//    }
}