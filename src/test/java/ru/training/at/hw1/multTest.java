package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class multTest {
    Calculator calc = new Calculator();

    @DataProvider(name = "multMethodDataProvider")
    public Object[][] multData() {
        return new Object[][]{
                {calc.mult(2, 3), 6},
                {calc.mult(0, 0), 0},
                {calc.mult(-2, 2), -4},
                {calc.mult(1.5, 2), 3},
                {calc.mult(10, 20), 200}
        };
    }

    @Test(dataProvider = "multMethodDataProvider")
    public void testMult(double result, double numbers){
        double delta = 0.01;
        Assert.assertEquals(result, numbers, delta);
    }
}
