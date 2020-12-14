package ru.geakbrains.level2.lesson7;

import java.lang.reflect.Method;

public class IntTest{
    private static int[] secondArgument;
    private static int[] firstArgument;
    private static boolean result = true;

    @BeforeSuite(firstArgument = {1,2,0,6,9}, secondArgument = {9,4,3,5,7})
    public void start() {
        for (Method m : this.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                BeforeSuite bs = m.getAnnotation(BeforeSuite.class);
                this.firstArgument = bs.firstArgument();
                this.secondArgument = bs.secondArgument();
                result = true;
            }
        }
    }

    @Test(operation = "DevToZero", priority = 10)
    public  boolean devToZero() {
        for (Method m : this.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                Test test = m.getAnnotation(Test.class);
                if (test.operation().equals("DevToZero")) {
                    for (int i = 0; i < secondArgument.length; i++) {
                        if (secondArgument[i] == 0) {

                            System.out.println(test.operation() + ": " + test.failMsg());
                            result = false;
                            return false;
                        }
                    }
                    System.out.println(test.operation() + ": " + test.okMsg());
                }
            }
        }
        return false;
    }

    @Test(operation = "isEqualArray", priority = 3)
    public  boolean isEqualArray() {
        for (Method m : this.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                Test test = m.getAnnotation(Test.class);
                if (test.operation().equals("isEqualArray")) {
                    if (this.firstArgument.length != secondArgument.length) {
                        System.out.println(test.operation() + ": " + test.failMsg());
                        result = false;
                        return false;
                    }
                    for (int i = 0; i < firstArgument.length; i++) {
                        if (firstArgument[i] != secondArgument[i]) {
                            System.out.println(test.operation() + ": " + test.failMsg());
                            result = false;
                            return false;
                        }
                    }
                    System.out.println(test.operation() + ": " + test.okMsg());

                }
            }
        }
        return true;
    }

     @AfterSuite
     public static void result() {
        System.out.println("Result of all test is "+ result);
     }
}
