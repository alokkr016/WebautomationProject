package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class WaitUtils {

    private WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_POLLING = 500;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementToBeVisible(WebElement element, int timeout) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e) {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        }
    }

    public WebElement waitForElementToBeClickable(WebElement element, int timeout) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException e) {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
        }
    }

    public WebElement fluentWait(final By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(DEFAULT_POLLING))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    public boolean waitForUrlToContain(String partialUrl, int timeout) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            return false;
        }
    }
}