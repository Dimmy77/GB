package ru.geakbrains.lessons_0;

public class Lesson2 {
    static public void main(String[] args){
        //1.	Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        // С помощью цикла и условия заменить 0 на 1, 1 на 0;
        int []arr1={0,1,1,1,0,0,0,1,1};
        int i;
//        System.out.println(Arrays.toString(arr1));
        System.out.println("Задание1");
        printArray("Исходный массив: ", arr1);
        for (i=0; i<arr1.length; i++){
            if (arr1[i]==0) {
                arr1[i]++;
            }
            else{
                arr1[i]--;
            }
            //Тернарный оператор
            //arr1[i]=(arr1[i]==1)? 0 : 1;
            //бинарно
            //arr1[i]=1-arr1[i];
        }

        printArray("\nПреобразованный массив: ", arr1);

        //2.	Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        System.out.println("\n\nЗадание2");
        int[] arr2=new int[8];
        fillProgressive(arr2,2);
        printArray("Результат задания: ", arr2);

        //3.	Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
        System.out.println("\n\nЗадание3");
        int[] arr3={1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray("Исходный массив: ", arr3);
        for (i=0; i<arr2.length; i++) {
            if(arr3[i]<6)arr3[i]*=2;
            //Тернарный оператор
            //arr3[i]=(arr3[i]<6)?arr3[i]*2:arr3[i];
        }
        printArray("\nПреобразованный массив: ", arr3);

        //4.	Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью
        // цикла(-ов) заполнить его диагональные элементы единицами;
        System.out.print("\n\nЗадание4");
        int[][] arr4=new int[10][10];
        for (i=0;i<arr4.length;i++){
            arr4[i][i]=arr4[i][arr4.length-1-i]=1;
            //arr4[i][arr4.length-1-i]=1;
            //String a = new String("\nСтрока "+ i);
            printArray("\nСтрока "+ i, arr4[i]);
        }

        //5.	** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        System.out.println("\n\nЗадание5");
        int min, max;
        i=1;
        min=max=arr3[0];
        do{
            if(arr3[i]<min) min=arr3[i];
            //min=(arr3[i]<min)?arr3[i]:min;
            if(arr3[i]>max) max=arr3[i];
            i++;
        }while (i<arr3.length-1);
        printArray("В массиве: ", arr3);
        System.out.println("\nМинимальное значение: "+min+"\nМаксимальное значение: "+max);


        //6.	** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть
        // true, если в массиве есть место, в котором сумма левой и правой части массива равны.
        // Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
        // граница показана символами ||, эти символы в массив не входят.
        System.out.println("\nЗадание 6");
        int[] arr6={2, 2, 2, 1, 2, 2, 10, 1};
        printArray("В масииве: ", arr3);
        System.out.println("\nБалланс: "+hasBalance(arr3));
        printArray("В масииве: ", arr6);
        System.out.println("\nБалланс: "+hasBalance(arr6));

        //7.	**** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
        // или отрицательным), при этом метод должен сместить все элементы массива на n позиций.
        // Для усложнения задачи нельзя пользоваться вспомогательными массивами
        System.out.println("\nЗадание 7");
        int n=7;
        printArray("Вход  массив: ", arr6);
        System.out.print(" Смещение="+n);
        printArray("\nВыход массив: ",moveArrayMember(arr6,n));

    }

    static private void fillProgressive(int[] array, int progressive){
        for (int i=0; i<array.length; i++){
            array[i]=i*progressive;
        }
    }

    static private void printArray (String message, int[] array){
        System.out.print(message + "(");
        int length = array.length;
        for (int i=0; i<length; i++){
            System.out.print(array[i]);
            if(i<length-1) System.out.print(", ");
        }
        System.out.print(")");
    }

    static private boolean hasBalance(int[] arg){
        int countLeft=0;
        for(int i=0;i<arg.length-1;i++){
            int countRight=0;
            countLeft+=arg[i];
            /*for(int j=0;j<i;j++){
                countLeft+=arg[j];
            }*/
            for(int j=i+1;j<arg.length;j++){
                countRight+=arg[j];
            }
            if(countLeft==countRight) return true;
        }
        return false;
        // Так же алгоритм можно построить посчитав сумму и вычитая потом элементы
    }

    static private int[] moveArrayMember(int[] arg, int n){
        int rotation = n%arg.length;
        if(n<0) rotation*=-1;
        else rotation=arg.length-rotation;
        int i, tmp;
        for (i=1; i<=rotation; i++){
            tmp=arg[0];
            for (int j=0;j<arg.length-1;j++){
                arg[j] = arg[j+1];
            }
            arg[arg.length-1]=tmp;
        }
        return arg;
    }

}
