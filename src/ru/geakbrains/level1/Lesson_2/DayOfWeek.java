package ru.geakbrains.level1.Lesson_2;

/*

4. Требуется реализовать enum DayOfWeek, который будет представлять дни недели.
С его помощью необходимо решить задачу определения кол-ва рабочих часов до конца недели по заднному текущему дню.

Возвращает кол-во оставшихся рабочих часов до конца
недели по заданному текущему дню. Считается, что
текущий день ещё не начался, и рабочие часы за него
должны учитываться.
*/

public enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static void getWorkingHours(DayOfWeek today){
        if (today == SUNDAY || today == SATURDAY){
            System.out.println("Today is weekend.");
        }
        else{
            System.out.println("Weekend begging from "+(5 - today.ordinal())*8 + " hours");
        }
    }
}


class DayOfWeekMain {
    public static void main(final String[] args) {
        DayOfWeek.getWorkingHours(DayOfWeek.MONDAY);
    }


}
