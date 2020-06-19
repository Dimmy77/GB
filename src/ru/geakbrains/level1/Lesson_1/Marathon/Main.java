package ru.geakbrains.level1.Lesson_1.Marathon;


public class Main {
    public static void main(String[] args) {
        Team team = new Team ("First", new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")); // Создаем команду
        Course c = new Course(new Cross(80), new Water(5), new Wall(1), new Cross(120)); //Создаем препятствия
        System.out.println("About team:");
        team.printTeam();  // Представляем команду

        System.out.println("\n****Start course! Shot! SHOT! SHOT!****");
        c.doIt(team); // Просим команду пройти полосу

        team.showResults(); // Показываем результаты

        team.printFinished(); //Показываем членов команды прошедших дистанцию


/*        Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
        Obstacle[] course = {new Cross(80), new Water(5), new Wall(1), new Cross(120)};
        for (Competitor c : competitors) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
        for (Competitor c : competitors) {
            c.info();
        }*/
    }
}