package ru.geakbrains.lessons_0;

public class Lesson5 {
    public static void main(String[] args){
        int quantity = 5;
        //4. Создать массив из 5 сотрудников
        Employee[] persons = new Employee[quantity];

        persons[0] = new Employee("Иванов", "Иван", "Иванович",
                "Директор", "IvanovII@gb.ru", "+7 (916) 111-11-11",
                100000, 50);
        persons[1] = new Employee("Петров", "Петр", "Петрович",
                "Маркетолог", "PetrovPP@gb.ru", "+7 (916) 111-11-12",
                25000, 31);
        persons[2] = new Employee("Сидоров", "Сидр", "Сидорович",
                "Инженер", "SidorovSS@gb.ru", "+7 (916) 111-11-13",
                20000, 41);
        persons[3] = new Employee("Маринина", "Мария", "Ивановна",
                "Бухгалтер", "MarininaMI@gb.ru", "+7 (916) 111-11-14",
                50000, 25);
        persons[4] = new Employee("Томаторина", "Гульнара", "Махмудовна",
                "Уборщик", "TomatorinaGM@gb.ru", "+7 (916) 111-11-15",
                15000, 47);
        printEmployee(persons,40);
    }

    //5. С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
    static void printEmployee(Employee[] a, int age){
        for(int i=0;i<a.length;i++){
            if(a[i].getAge()>age) {
                a[i].printConsole();
            }
        }
    }
}
