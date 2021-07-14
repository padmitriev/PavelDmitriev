package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JdiPage {

    WebDriver driver;
    final String URL = "https://jdi-testing.github.io/jdi-light/index.html";
    @FindBy(id = "user-icon")
    private WebElement icon;
    @FindBy(id = "name")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(id = "user-name")
    private WebElement loggedName;
    @FindBy(xpath = "//ul[contains(@class,'uui-navigation nav navbar-nav m-l8')]")
    private WebElement headerMenu;
    @FindBy(tagName = "img")
    private List<WebElement> images;
    @FindBy(tagName = "iframe")
    private List<WebElement> iframes;
    @FindBy(xpath = "//input[@id='frame-button']")
    private List<WebElement> frameButton;
    @FindBy(xpath = "//ul[contains(@class,'sidebar-menu left')]")
    private WebElement leftSection;
    @FindBy(xpath = "//iframe[@id='first-frame']")
    private WebElement firstFrame;
    @FindBy(xpath = "//div[contains(@class,'fa fa-caret-down arrow')]")
    private WebElement serviceMenu;
    @FindBy(xpath = "//label[contains(@class,'label-checkbox')]")
    private List<WebElement> checkbox;
    @FindBy(xpath = "//ul[contains(@class,'panel-body-list logs')]")
    private WebElement logPanel;
    @FindBy(xpath = "//label[contains(@class,'label-radio')]")
    private List<WebElement> radio;
    @FindBy(xpath = "//select[@class='uui-form-element']")
    private WebElement selectedElement;
    @FindBy(xpath = "//div[contains(@class,'benefit-icon')]")
    private List<WebElement> allImages;
    @FindBy(xpath = "//span[contains(@class,'benefit-txt')]")
    private List<WebElement> allTexts;

    public void setup() {
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public JdiPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getURL() {
        return URL;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void login() {
       icon.click();
       username.sendKeys("Roman");
       password.sendKeys("Jdi1234");
       loginButton.click();
    }

    public WebElement getLoggedName() {
        return loggedName;
    }

    public List<WebElement> getHeaderMenu() {
        return headerMenu.findElements(By.xpath("./li"));
    }


    public List<WebElement> getImages() {
        ArrayList<WebElement> allImages = new ArrayList<>();
        allImages.addAll(images);
        allImages.add(getImageFromFirstFrame());
        return allImages;
    }

    public WebElement getImageFromFirstFrame() {
        driver.switchTo().frame(firstFrame);
        return driver.findElement(By.tagName("img"));
    }

    public List<WebElement> getIframes() {
        return iframes;
    }

    public List<WebElement> getFrameButton() {
        return frameButton;
    }

    public void switchTo(String frame) {
        driver.switchTo().frame("frame");
    }

    public String getActualWindow() {
        String actualWindow = driver.getWindowHandle();
        return driver.switchTo().window(actualWindow).getTitle();
    }

    public List<WebElement> getLeftSection() {
        return leftSection.findElements(By.xpath("./li"));
    }

    public void openDiffElements() {
        serviceMenu.click();
        WebElement diffElements = driver.findElement(By.xpath("//span[text()='Different elements']"));
        diffElements.click();
    }

    public List<WebElement> getCheckbox() {
        return  checkbox;
    }

    public List<WebElement> getLogElements() {
        return logPanel.findElements(By.xpath("./li"));
    }

    public List<WebElement> getRadio() {
        return radio;
    }

    public WebElement getLogMessage() {
        return logPanel.findElement(By.xpath("./li"));
    }

    public List<WebElement> getAllLogMessages() {
        return logPanel.findElements(By.xpath("./li"));
    }

    public WebElement getSelectedElement() {
        return selectedElement;
    }

    public int getAllimagesSize() {
        return allImages.size();
    }

    public List<WebElement> getAllImages() {
        return allImages;
    }

    public List<WebElement> getAllTexts() {
        return allTexts;
    }

    public String[] getExpectedTexts() {
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
        return texts;
    }




}
