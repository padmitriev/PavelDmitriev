package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class sumTest {
    Calculator calc = new Calculator();

    @DataProvider(name = "sumMethodDataProvider")
    public Object[][] sumData() {
        return new Object[][]{
                {calc.sum(2, 3), 5},
                {calc.sum(0, 0), 0},
                {calc.sum(-2, 20), 18},
                {calc.sum(-30, -70), -100},
                {calc.sum(3000, 7000), 10000}
        };
    }

    @Test(dataProvider = "sumMethodDataProvider")
    public void testSum(double result, double numbers){
        double delta = 0.01;
        Assert.assertEquals(result, numbers, delta);
    }
}
