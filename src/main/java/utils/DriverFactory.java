package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void initializeDriver(String browser, boolean isRemote,String url) {
        WebDriver webDriver = null;

        try {
            if (isRemote) {
                String remoteUrl = ConfigReader.get("remoteUrl");
                if (Objects.equals(browser.toLowerCase(), "chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    webDriver = new RemoteWebDriver(new URL(remoteUrl), options);
                } else if (Objects.equals(browser.toLowerCase(), "firefox")) {
                    FirefoxOptions options = new FirefoxOptions();
                    webDriver = new RemoteWebDriver(new URL(remoteUrl), options);
                } else {
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            } else {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        webDriver = new ChromeDriver(chromeOptions);
                        break;
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        webDriver = new FirefoxDriver(firefoxOptions);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            }

            webDriver.get(url);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            webDriver.manage().window().maximize();
            setDriver(webDriver);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize the WebDriver: " + e.getMessage());
        }
    }

    public static void quitDriver() {
        WebDriver webDriver = getDriver();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}