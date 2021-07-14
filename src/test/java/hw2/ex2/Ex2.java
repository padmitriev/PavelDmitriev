package hw2.ex2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Ex2 {

    private WebDriver driver;

    @BeforeTest
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void ex2() {
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

//  5. Open through the header menu Service -> Different Elements Page
        WebElement serviceMenu = driver.findElement(By.xpath("//div[contains(@class,'fa fa-caret-down arrow')]"));
        serviceMenu.click();
        WebElement diffElements = driver.findElement(By.xpath("//span[text()='Different elements']"));
        diffElements.click();
        assertEquals(driver.getCurrentUrl(), "https://jdi-testing.github.io/jdi-light/different-elements.html");

//  6. Select checkboxes
        // .isSelected() doesn't work
        List<WebElement> checkbox = driver.findElements(By.xpath("//label[contains(@class,'label-checkbox')]"));
        checkbox.get(0).click();
        checkbox.get(2).click();
        assertEquals(checkbox.get(0).getText(), "Water");
        assertEquals(checkbox.get(2).getText(), "Wind");

        WebElement logPanel = driver.findElement(By.xpath("//ul[contains(@class,'panel-body-list logs')]"));
        List<WebElement> logMessages = logPanel.findElements(By.xpath("./li"));
        String firstLogMessage = "";
        String secondlogMessage = "";

        if (logMessages.size() > 1) {
            firstLogMessage = logMessages.get(logMessages.size() - 2).getText();
            secondlogMessage = logMessages.get(logMessages.size() - 1).getText();
        }

        assertTrue(firstLogMessage.contains("true"));
        assertTrue(secondlogMessage.contains("true"));

//  7. Select radio
        List<WebElement> radio = driver.findElements(By.xpath("//label[contains(@class,'label-radio')]"));
        WebElement radioSelen = null;
        for (WebElement element : radio) {
            if (element.getText().equals("Selen")) {
                radioSelen = element;
                break;
            }
        }
        assert radioSelen != null;
        radioSelen.click();
        WebElement logMessageRadio = logPanel.findElement(By.xpath("./li"));
        assertTrue(logMessageRadio.getText().contains("Selen"));

//  8. Select in dropdown
        Select select = new Select(driver.findElement(By.xpath("//select[@class='uui-form-element']")));
        select.selectByVisibleText("Yellow");
        WebElement logMessageDropdown = logPanel.findElement(By.xpath("./li"));
        assertTrue(logMessageDropdown.getText().contains("Yellow"));

//  9. Assert that
//•	for each checkbox there is an individual log row and value is corresponded to the status of checkbox
//•	for radio button there is a log row and value is corresponded to the status of radio button
//•	for dropdown there is a log row and value is corresponded to the selected value
        List<WebElement> allLogMessages = logPanel.findElements(By.xpath("./li"));
        boolean waterIsOk = false;
        boolean windIsOk = false;
        boolean selenIsOk = false;
        boolean yellowIsOk = false;

        for (WebElement element : allLogMessages) {
            if (element.getText().contains("Water") && element.getText().contains("true")) {
                waterIsOk = true;
            }
            if (element.getText().contains("Wind") && element.getText().contains("true")) {
                windIsOk = true;
            }
            if (element.getText().contains("metal") && element.getText().contains("Selen")) {
                selenIsOk = true;
            }
            if (element.getText().contains("Colors") && element.getText().contains("Yellow")) {
                yellowIsOk = true;
            }
        }
        assertTrue(waterIsOk);
        assertTrue(windIsOk);
        assertTrue(selenIsOk);
        assertTrue(yellowIsOk);

//  10. Close Browser
        driver.quit();
        assertEquals(((RemoteWebDriver) driver).getSessionId(), null);
    }
}