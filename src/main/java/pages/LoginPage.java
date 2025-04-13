package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerUtils;
import utils.WaitUtils;

public class LoginPage {
    private WebDriver driver;
    private WaitUtils waitUtils;

    // Define WebElements using @FindBy annotation
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "#login > button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='flash']")
    private WebElement message;

    // Constructor to initialize WebElements
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    // Method to perform login action
    public void login(String username, String password) {
        waitUtils.waitForElementToBeVisible(usernameField,10).sendKeys(username);
        waitUtils.waitForElementToBeVisible(passwordField,10).sendKeys(password);
        waitUtils.waitForElementToBeClickable(loginButton, 10).click();
    }

    // Method to retrieve login message
    public String getMessage() {
        String msg = waitUtils.waitForElementToBeVisible(message,10).getText();
        LoggerUtils.reportLog(LoggerUtils.LogsType.INFO, "Login message on screen: " + msg);
        return msg;
    }

    // Method to clean up message text (removes extra characters like ×)
    public String getCleanMessage() {
        String msg = message.getText().trim().replace("×", "").trim();
        LoggerUtils.reportLog(LoggerUtils.LogsType.INFO, "Cleaned login message: " + msg);
        return msg;
    }

    public boolean isInSecureArea() {
        return driver.getCurrentUrl().contains("/secure");
    }
}