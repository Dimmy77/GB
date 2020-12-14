package ru.geakbrains.level2.lesson7;

public class Test1 {

    public static void main(String[] args){
        final int LENGTH = 20;
        int[][] arr = new  int[LENGTH][LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                int tmp = Math.min(Math.min(i,j), Math.min(LENGTH-1-i, LENGTH-1-j));
                arr[i][j] = LENGTH*LENGTH+1-(i>j? (LENGTH-2*tmp-2)*(LENGTH-2*tmp-2)+(i-tmp)+(j-tmp)
                        :(LENGTH-2*tmp) * (LENGTH-2*tmp)-(i-tmp)-(j-tmp));
                System.out.print(arr[i][j]+", ");
            }
            System.out.println("\n");
        }
    }
}
