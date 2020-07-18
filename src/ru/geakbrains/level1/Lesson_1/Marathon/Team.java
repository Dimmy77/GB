package ru.geakbrains.level1.Lesson_1.Marathon;

/*
Добавить класс Team, который будет содержать: название команды, массив из 4-х участников (т.е. в конструкторе можно
        сразу всех участников указывать), метод для вывода информации о членах команды прошедших дистанцию,
        метод вывода информации обо всех членах команды.
*/

public class Team {
    private String name;
    private Competitor team[];
    private int member = 0;

    Team (String name, Competitor... team){
        this.name=name;
        this.team = team;
    }

    public Competitor getMember(){
        member++;
        return team[member-1];
    }

    public void setCurrentNumberOfMember(int member){
        this.member = member;
    }

    public int getCurrentNumberOfMember(){
        return member;
    }

    public int getNumberOfMember(){
        return team.length;
    }

    public void printFinished(){
        System.out.println("\nList of member team "+name+" finished course:");
        for (Competitor a: team) {
            if (a.isOnDistance()) a.info();
        }
    }

    public void showResults(){
        boolean resultOfTeam=true;
        System.out.println("\nResult member of team "+name+" next:");
        for (Competitor a: team) {
            a.info();
            resultOfTeam &= a.isOnDistance();
        }
        System.out.println("Result of team "+name+" is: " + resultOfTeam);
    }

    public void printTeam(){
        System.out.println("\nTeam: "+name+" can:");
        for (Competitor a: team) {
            a.printAll();
        }
    }

}
