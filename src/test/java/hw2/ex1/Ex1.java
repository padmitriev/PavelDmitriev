package hw2.ex1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class Ex1 {

    private WebDriver driver;

    @BeforeTest
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void ex1() {
//  1. Open test site by URL
        String jdiUrl = "https://jdi-testing.github.io/jdi-light/index.html";
        System.out.println(driver.getCurrentUrl());
        assertEquals(driver.getCurrentUrl(),jdiUrl);

//  2. Assert Browser title
        String actualTitle = driver.getTitle();
        String expectedTitle = "Home Page";
        assertEquals(actualTitle, expectedTitle);

//  3. Perform login
        WebElement icon = driver.findElement(By.id("user-icon"));
        icon.click();
        WebElement username = driver.findElement(By.id("name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        username.sendKeys("Roman");
        password.sendKeys("Jdi1234");
        loginButton.click();

//  4. Assert Username is logged in
        assertTrue(driver.findElement(By.id("user-name")).isDisplayed());
        String name = driver.findElement(By.xpath("//*[@id='user-name']")).getText();
        assertEquals(name, "ROMAN IOVLEV");

//  5. Assert that there are 4 items on the header section are displayed and they have proper texts
        WebElement headerClass = driver.findElement(By.xpath("//ul[contains(@class,'uui-navigation nav navbar-nav m-l8')]"));
        List<WebElement> headerElements = headerClass.findElements(By.xpath("./li"));
        for (WebElement element : headerElements ) {
            assertTrue(element.isDisplayed());
        }
        assertEquals(headerElements.get(0).getText(), "HOME");
        assertEquals(headerElements.get(1).getText(), "CONTACT FORM");
        assertEquals(headerElements.get(2).getText(), "SERVICE");
        assertEquals(headerElements.get(3).getText(), "METALS & COLORS");

//  6. Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> allImages = driver.findElements(By.xpath("//div[contains(@class,'benefit-icon')]"));
        assertEquals(allImages.size(), 4);

//  7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> allTexts = driver.findElements(By.xpath("//span[contains(@class,'benefit-txt')]"));
        String[] texts =
                {"To include good practices\n" +
                        "and ideas from successful\n" +
                        "EPAM project",
                        "To be flexible and\n" +
                                "customizable",
                        "To be multiplatform",
                        "Already have good base\n" +
                                "(about 20 internal and\n" +
                                "some external projects),\n" +
                                "wish to get more…"};
        for (int i=0; i<allImages.size(); i++) {
            assertEquals(allTexts.get(i).getText(), texts[i]);
        }

//  8. Assert that there is the iframe with “Frame Button” exist
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        boolean ifButtonExists = false;

        for (WebElement iframe : iframes) {
            driver.switchTo().frame(iframe);
                if (driver.findElement(By.id("frame-button")) != null) {
                    ifButtonExists = true;
                    break;
                }
        }
        driver.switchTo().defaultContent();
        assertTrue(ifButtonExists);

//  9. Switch to the iframe and check that there is “Frame Button” in the iframe
        driver.switchTo().frame("frame");
        assertFalse(driver.findElements(By.xpath("//input[@id='frame-button']")).isEmpty());

//  10. Switch to original window back
        driver.switchTo().defaultContent();
        String actualWindow = driver.getWindowHandle();
        assertEquals(driver.switchTo().window(actualWindow).getTitle(), "Home Page");

//  11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        WebElement leftSection = driver.findElement(By.xpath("//ul[contains(@class,'sidebar-menu left')]"));
        List<WebElement> leftElements = leftSection.findElements(By.xpath("./li"));

        for (WebElement webElement : leftElements) {
            assertTrue(webElement.isDisplayed());
        }
        assertEquals(leftElements.get(0).getText(),"Home");
        assertEquals(leftElements.get(1).getText(),"Contact form");
        assertEquals(leftElements.get(2).getText(),"Service");
        assertEquals(leftElements.get(3).getText(),"Metals & Colors");
        assertEquals(leftElements.get(4).getText(),"Elements packs");

//  12. Close Browser
        driver.quit();
        assertEquals(((RemoteWebDriver) driver).getSessionId(), null);
    }
}
