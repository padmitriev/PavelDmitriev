import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class sumTest {
    Calculator calc = new Calculator();

//    @DataProvider(name = "sumMethodDataProvider")
//    public Object[][] sumData() {
//        return new Object[][]{
//                {5, calc.sum(2, 3)},
//                {0, calc.sum(0, 0)},
//                {0, calc.sum(-2, 2)},
//                {-100, calc.sum(-30, -70)},
//                {10000, calc.sum(3000, 7000)},
//        };
//    }

    @Test(groups = {"sum and sub"})
    public void testSum(){
        double a = 2;
        double b = 3;
        double actual = calc.sum(a, b);
        double expected = 5;
        double delta = 0.01;
        Assert.assertEquals(actual, expected, delta);
    }
}
