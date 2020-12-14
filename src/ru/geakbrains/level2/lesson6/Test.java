package ru.geakbrains.level2.lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
            {0,0,0,1,4,3,6,7,8,9},
            {0,1,2,3,4,5,6,7,8,9},
            {2,2,2,1,5,5,5,5},
            {2,2,2,0,5,5,5,5}
        });
    }

    private int[] a;
    Main mn;

    public Test(int... a){
        this.a =a;
    }

    @Before
    public void init(){
        mn = new Main(a);

    }

    @org.junit.Test
    public void test(){
        mn = new Main(a);
        Assert.assertTrue(mn.hasOneOrFore());
    }
}
