package tests.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "isRemote"})
    public void setUp(@Optional("chrome") String browser, @Optional("false") boolean isRemote) throws InterruptedException {
        DriverFactory.initializeDriver(browser, isRemote);
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}