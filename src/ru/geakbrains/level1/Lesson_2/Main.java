package ru.geakbrains.level1.Lesson_2;

public class Main {
//3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и
//    MyArrayDataException, и вывести результат расчета.
    public static void main(String[] arg) {
        MyArray a = new MyArray(4, new String[][]{{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"0", "0", "0", "0"}, {"0", "0", "0", "0"}});
        System.out.println("Summ is: "+a.sum());
    }
}

class MyArray {
    int size = 4;
    String[][] arr;

    MyArray(int size, String[][] arr) {
        this.arr = arr;
        this.size = size;
    }

    //1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4, при подаче массива другого
//    размера необходимо бросить исключение MyArraySizeException.
    int sum(){
        if (arr.length != size || arr[0].length != size) throw new MyArraySizeException(size);
        int sum = 0;
        int i, j;

//    2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
//    Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
//    должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Index of wrong member is: " + i + "х" + j);
                    //e.printStackTrace();
                }
            }

        }
        return sum;
    }
}

class MyArraySizeException extends ArrayIndexOutOfBoundsException{
    public MyArraySizeException(int size) {
        super();
        System.out.println("Array length don't equal: " + size + "х"+size);
    }
}

class MyArrayDataException extends NumberFormatException{
    public  MyArrayDataException(String msg){
        super();
        System.out.println("Array content don't int member.\n"+msg);
    }
}