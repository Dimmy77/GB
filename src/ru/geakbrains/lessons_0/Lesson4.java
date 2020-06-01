package ru.geakbrains.lessons_0;

import java.util.Arrays;
import java.util.Scanner;

public class Lesson4 {
    static char[][] map;
    static final int SIZE = 5;
    static final int DOTS_TO_WIN=4;
    static int lastHumanX, lastHumanY;

    static final char DOT_EMPTY = '.';
    static final char DOT_X = 'X';
    static final char DOT_0 = '0';

    public static void main (String[] args){
        initMap();
        do{
            printMap();
            doHumanTurn();
            printMap();
            if (isWinner(DOT_X, DOTS_TO_WIN)){
                System.out.println("Человек победил!");
                break;
            }
            else if(isMapFull()){
                System.out.println("Ничья!");
                break;
            }
            doAiTurn();
            printMap();
            if (isWinner(DOT_0, DOTS_TO_WIN)){
                System.out.println("Компьютер победил!");
                break;
            }
            else if(isMapFull()){
                System.out.println("Ничья!");
                break;
            }
        }while(true);
    }

    static void initMap(){
        map = new char[SIZE][SIZE];
        for(int i = 0; i<SIZE;i++){
            Arrays.fill(map[i], DOT_EMPTY);
        }
    }

    static void printMap(){
        for (int i=0; i<=SIZE;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i = 0; i<SIZE;i++) {
            System.out.print((i+1)+" ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void doHumanTurn(){
        do{

            lastHumanY=getNumbetFromConcole()-1;
            lastHumanX=getNumbetFromConcole()-1;
        }while (!isCellValid(lastHumanX,lastHumanY));
        map[lastHumanY][lastHumanX]=DOT_X;
    }

    static int getNumbetFromConcole(){
        Scanner sc=new Scanner(System.in);
        do{
            if (sc.hasNextInt()){
                return sc.nextInt();
            }
            System.out.println("Введите целое число");
            sc.nextLine();
        }while (true);
    }

    static boolean isCellValid(int x, int y){
        if(x<0 || x>=SIZE || y<0||y>=SIZE){
            return false;
        }

        return map[y][x] == DOT_EMPTY;
    }

    static void doAiTurn(){
        int x;
        int y;

        do{
            y=(int)(Math.random()*SIZE);
            x=(int)(Math.random()*SIZE);
        }while (!isCellValid(x,y));
        System.out.println("Ход компьютера Y X:"+y+" "+x);
        map[y][x]=DOT_0;
    }

    static boolean isMapFull(){
        for (int i=0; i<SIZE;i++) {
            for (int j = 0; j < SIZE; j++) {
                if(map[i][j] == DOT_EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isWinner(char symbol, int dotsLength){
        int countH=0;
        int countV=0;
        int countD1=0;
        int countD2=0;

        for (int i=0; i<SIZE;i++) {
            for (int j=0;j<SIZE;j++){
                if (map[i][j]==symbol){
                    countH++;
                }
                else{
                    countH=0;
                }
                if (map[j][i]==symbol){
                    countV++;
                }
                else{
                    countV=0;
                }
                if(countH==dotsLength||countV==dotsLength){
                    return true;
                }
            }
            countH=0;
            countV=0;
            if(map[i][i]==symbol){
                countD1++;
            }
            else{
                countD1=0;
            }
            if (map[map[i].length-i-1][i]==symbol){
                countD2++;
            }
            else{
                countD2=0;
            }

            if(countD1==dotsLength||countD2==dotsLength){
                return true;
            }
        }
        return false;
    }
}
