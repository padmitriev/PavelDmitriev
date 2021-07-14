package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import pages.JdiPage;

import java.util.List;

import static org.testng.Assert.*;

public class Ex2 {
    private WebDriver driver;

    @BeforeTest
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void ex1() {
        JdiPage page = new JdiPage(driver);
        page.setup();
//  1. Open test site by URL
        assertEquals(driver.getCurrentUrl(), page.getURL());

//  2. Assert Browser title
        String expectedTitle = "Home Page";
        assertEquals(page.getPageTitle(), expectedTitle);

//  3. Perform login
        page.login();

//  4. Assert Username is logged in
        assertTrue(page.getLoggedName().isDisplayed());
        assertEquals(page.getLoggedName().getText(), "ROMAN IOVLEV");

//  5. Open through the header menu Service -> Different Elements Page
        page.openDiffElements();
        assertEquals(driver.getCurrentUrl(), "https://jdi-testing.github.io/jdi-light/different-elements.html");

//  6. Select checkboxes
        // .isSelected() doesn't work
        List<WebElement> checkbox = page.getCheckbox();
        checkbox.get(0).click();
        checkbox.get(2).click();
        assertEquals(checkbox.get(0).getText(), "Water");
        assertEquals(checkbox.get(2).getText(), "Wind");

        List<WebElement> logMessages = page.getLogElements();
        String firstLogMessage = "";
        String secondlogMessage = "";

        if (logMessages.size() > 1) {
            firstLogMessage = logMessages.get(logMessages.size() - 2).getText();
            secondlogMessage = logMessages.get(logMessages.size() - 1).getText();
        }
        assertTrue(firstLogMessage.contains("true"));
        assertTrue(secondlogMessage.contains("true"));

//  7. Select radio
        List<WebElement> radio = page.getRadio();
        WebElement radioSelen = null;
        for (WebElement element : radio) {
            if (element.getText().equals("Selen")) {
                radioSelen = element;
                break;
            }
        }
        assert radioSelen != null;
        radioSelen.click();
        WebElement logMessageRadio = page.getLogMessage();
        assertTrue(logMessageRadio.getText().contains("Selen"));

//  8. Select in dropdown
        Select select = new Select(page.getSelectedElement());
        select.selectByVisibleText("Yellow");
        WebElement logMessageDropdown = page.getLogMessage();
        assertTrue(logMessageDropdown.getText().contains("Yellow"));

//  9. Assert that
//•	for each checkbox there is an individual log row and value is corresponded to the status of checkbox
//•	for radio button there is a log row and value is corresponded to the status of radio button
//•	for dropdown there is a log row and value is corresponded to the selected value
        List<WebElement> allLogMessages = page.getAllLogMessages();
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
