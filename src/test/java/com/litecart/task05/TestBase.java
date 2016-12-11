package com.litecart.task05;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

/**
 * Created by pshynin on 11/17/16.
 */
public class TestBase {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    protected WebDriver driver;
    protected WebDriverWait wait;

    boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        }catch (InvalidSelectorException ex) {
            throw ex;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @BeforeSuite
    private void setUp() throws IOException {

        String browser = BrowserType.FIREFOX;

        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 1000);
            return;
        }

        driver = new ChromeDriver();
        tlDriver.set(driver);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, false);
    }


    @AfterSuite(alwaysRun = true)
    private void tearDown() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                }));
    }
}