package ru.geakbrains.level1.Lesson_1.Marathon;

/*
Добавить класс Course (полоса препятствий), в котором будут находиться: массив препятствий,
        метод который будет просить команду пройти всю полосу;
*/

public class Course {
    private Obstacle obstacle[];

    Course(Obstacle... obstacle){
        this.obstacle=obstacle;
    }

    public void doIt(Team team){
        Competitor competitor;
        for (int i=0; i < team.getNumberOfMember();i++) {
            competitor = team.getMember();
            for (Obstacle o : obstacle) {
                o.doIt(competitor);
                if (!competitor.isOnDistance()) break;
            }
        }
    }
}
