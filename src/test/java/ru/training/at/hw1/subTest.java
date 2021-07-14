package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class subTest {
    Calculator calc = new Calculator();

    @DataProvider(name = "subMethodDataProvider")
    public Object[][] subData() {
        return new Object[][]{
                {calc.sub(2, 3), -1},
                {calc.sub(0, 0), 0},
                {calc.sub(-2, 20), -22},
                {calc.sub(-30, -70), 40},
                {calc.sub(3000, 7000), -4000}
        };
    }

    @Test(dataProvider = "subMethodDataProvider")
    public void testSub(double result, double numbers){
        double delta = 0.01;
        Assert.assertEquals(result, numbers, delta);
    }
}
