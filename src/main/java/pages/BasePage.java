package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;


public class BasePage {
    protected WebDriver driver;
    protected WaitUtils waitUtils;
    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
    }

}
