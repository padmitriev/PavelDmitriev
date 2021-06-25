package ru.training.at.hwl;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class multTest {
    Calculator calc = new Calculator();

    @Test(groups = {"mult and div"})
    public void testMult(){
        double a = 2.5;
        double b = 4;
        double actual = calc.mult(a, b);
        double expected = 10;
        double delta = 0.01;
        Assert.assertEquals(actual, expected, delta);
    }
}
