package Lesson_1;

public class Animal {
    private int a;
    protected int z;

    public Animal(int a) {
      //  System.out.println("Animal");
        this.a = a;
    }

    void test() {
        System.out.println("Animal test");
    }
}

class Cat extends Animal {
    private int b;
    protected int z;

    public Cat(int b, int z) {
        super(10);
      //  System.out.println("Cat");
        this.b = b;
        this.z = z;
    }

    void test() {
        System.out.println("Cat test");
    }
}

class SuperCat extends Cat {

    public SuperCat(int b, int z) {
        super(b, z);
       // System.out.println("SuperCat");
    }

    void test() {
        System.out.println("SuperCat test");
    }
}

class MainTest {
    public static void main(String[] args) {
       // SuperCat superCat = new SuperCat(10,11);

        Animal[] mass = {new Animal(1), new Cat(1,2), new SuperCat(1,2)};

        for (Animal o : mass) {
            o.test();
        }

    }
}