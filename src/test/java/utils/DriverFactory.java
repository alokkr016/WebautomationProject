package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
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

    public static void initializeDriver(String browser, boolean isRemote) {
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
                }
            } else {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        webDriver = new ChromeDriver();
                        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        break;
                    case "firefox":
                        webDriver = new FirefoxDriver();
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize the WebDriver: " + e.getMessage());
        }

        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}