package ru.training.at.hwl;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class subTest {
    Calculator calc = new Calculator();

    @Test(groups = {"sum and sub"})
    public void testSub(){
        double a = 7;
        double b = 3;
        double actual = calc.sub(a, b);
        double expected = 4;
        double delta = 0.01;
        Assert.assertEquals(actual, expected, delta);
    }
}
