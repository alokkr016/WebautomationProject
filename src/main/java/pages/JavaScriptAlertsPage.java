package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import utils.LoggerUtils;

public class JavaScriptAlertsPage extends BasePage{

    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    private WebElement jsAlertButton;

    @FindBy(xpath = "//button[text()='Click for JS Confirm']")
    private WebElement jsConfirmButton;

    @FindBy(xpath = "//button[text()='Click for JS Prompt']")
    private WebElement jsPromptButton;

    @FindBy(id = "result")
    private WebElement resultMessage;

    public JavaScriptAlertsPage() {
        super();
    }

    public void triggerJSAlert() {
        waitUtils.waitForElementToBeClickable(jsAlertButton, 10).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        LoggerUtils.reportLog(LoggerUtils.LogsType.INFO, "Triggered JS Alert and accepted it.");
    }

    public void triggerJSConfirm(boolean accept) {
        waitUtils.waitForElementToBeClickable(jsConfirmButton, 10).click();
        Alert alert = driver.switchTo().alert();
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        LoggerUtils.reportLog(LoggerUtils.LogsType.INFO, "Triggered JS Confirm and " + (accept ? "accepted" : "dismissed") + " it.");
    }

    public void triggerJSPrompt(String input, boolean accept) {
        waitUtils.waitForElementToBeClickable(jsPromptButton, 10).click();
        Alert alert = driver.switchTo().alert();
        if (input != null) {
            alert.sendKeys(input);
        }
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        LoggerUtils.reportLog(LoggerUtils.LogsType.INFO, "Triggered JS Prompt with input: '" + input + "' and " + (accept ? "accepted" : "dismissed") + " it.");
    }

    public String getResultMessage() {
        String message = waitUtils.waitForElementToBeVisible(resultMessage, 10).getText();
        LoggerUtils.reportLog(LoggerUtils.LogsType.INFO, "Result message displayed: " + message);
        return message;
    }
}