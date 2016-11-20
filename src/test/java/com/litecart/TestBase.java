package com.litecart;

import com.litecart.ApplicationManager.ApplicationManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestBase extends ApplicationManager {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public Properties properties;
    public WebDriver wd;
    public WebDriverWait wait;

    Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @BeforeSuite
    public void setUp() throws IOException {
        String browser = BrowserType.FIREFOX;
//        String target = System.getProperty("target", "local");
//        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (tlDriver.get() != null) {
            wd = tlDriver.get();
            wait = new WebDriverWait(wd, 0);
            return;
        }
        if (browser.equals(BrowserType.FIREFOX)) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(FirefoxDriver.MARIONETTE, false);
            wd = new FirefoxDriver(caps);
            tlDriver.set(wd);
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
            tlDriver.set(wd);
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
            tlDriver.set(wd);
        } else if (browser.equals(BrowserType.SAFARI)) {
            wd = new SafariDriver();
            tlDriver.set(wd);
        }

        System.out.println(((HasCapabilities) wd).getCapabilities());
        wait = new WebDriverWait(wd, 0);

//        wd.get(properties.getProperty("web.url"));
//        login(properties.getProperty("web.username"), properties.getProperty("web.password"));
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    wd.quit();
                    wd = null;
                }));
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p) {
        logger.info("Stop test " + m.getName());
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }


    public void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.name("login"));
    }

    void logout() {
        click(By.xpath(".//*[@id='sidebar']/div[2]/a[5]/i"));
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
