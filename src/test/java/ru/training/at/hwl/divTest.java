package ru.training.at.hwl;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class divTest {
    Calculator calc = new Calculator();

    @Test(groups = {"mult and div"})
    public void testDiv(){
        double a = 15;
        double b = 5;
        double actual = calc.div(a, b);
        double expected = 3;
        double delta = 0.01;
        Assert.assertEquals(actual, expected, delta);
    }
}
