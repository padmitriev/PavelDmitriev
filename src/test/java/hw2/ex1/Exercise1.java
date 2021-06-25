package hw2.ex1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

// SORRY, это все, что успел сделать за сегодня, 25.06 (вчера только сдал HW1). Печаль((

public class Exercise1 {

    @Test
    public void exercise1 () {
        System.setProperty("webdriver.chrome.driver", "C://Selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");
        driver.manage().window().maximize();

        SoftAssert step2 = new SoftAssert();
        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";
        step2.assertEquals(actualTitle, expectedTitle);
        step2.assertAll();

        SoftAssert step6 = new SoftAssert();
        int counter = 0;
        List<WebElement> listImages = driver.findElements(By.tagName("img"));
        step6.assertEquals(listImages.size(), 4);
        for(WebElement image:listImages) {
            if(image.isDisplayed()) {
                counter++;
            }
        }
        step6.assertEquals(counter, 4);
        step6.assertAll();

//        String ifexists = driver.findElement(By.xpath("//*[@class='uui-form-element']")).getText();
//        System.out.println(ifexists);

        driver.close();
    }
}
