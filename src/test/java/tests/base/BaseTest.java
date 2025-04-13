package tests.base;

import org.testng.annotations.*;

import utils.DriverFactory;

public class BaseTest {
    @BeforeMethod
    @Parameters({"browser", "isRemote", "url"})
    public void setUp(@Optional("chrome") String browser,
                      @Optional("false") String isRemote,String url) {

        // Use system properties if present, otherwise fallback to parameter
        String finalBrowser = System.getProperty("browser", browser);
        boolean finalIsRemote = Boolean.parseBoolean(System.getProperty("isRemote", isRemote));
        String finalUrl = System.getProperty("url", url);

        DriverFactory.initializeDriver(finalBrowser, finalIsRemote, finalUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}