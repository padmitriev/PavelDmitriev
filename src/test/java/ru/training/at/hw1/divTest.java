package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class divTest {
    Calculator calc = new Calculator();

    @DataProvider(name = "divMethodDataProvider")
    public Object[][] divData() {
        return new Object[][]{
                {calc.div(3, 3), 1},
                {calc.div(0, 10), 0},
                {calc.div(-4, -2), 2},
                {calc.div(100, 10), 10},
                {calc.div(6000, 20), 300}
        };
    }

    @Test(dataProvider = "divMethodDataProvider")
    public void testDiv(double result, double numbers){
        double delta = 0.01;
        Assert.assertEquals(result, numbers, delta);
    }
}
