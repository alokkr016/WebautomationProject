package tests.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseTest {
    protected WebDriver driver;
    private static boolean isDriverInitialized = false;

    @BeforeMethod
    @Parameters({"browser", "isRemote","url"})
    public void setUp(@Optional("chrome") String browser, @Optional("false") boolean isRemote,String url) throws InterruptedException {
            DriverFactory.initializeDriver(browser, isRemote,url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}