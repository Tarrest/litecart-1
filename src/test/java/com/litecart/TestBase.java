package com.litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    WebDriver wd;
    private Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @BeforeSuite
    public void setUp() throws IOException {
        Properties properties = new Properties();
        String file = "env.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
        properties.load(inputStream);

        String browser = System.getProperty("app.browser", BrowserType.FIREFOX);

        if (driver.get() != null) {
            wd = driver.get();
            return;
        }

        if (browser.equals(BrowserType.FIREFOX)) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(FirefoxDriver.MARIONETTE, false);
            wd = new FirefoxDriver(caps);
            driver.set(wd);
        } else if (browser.equals(BrowserType.CHROME)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-fullscreen");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            wd = new ChromeDriver();
            driver.set(wd);
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
            driver.set(wd);
        } else if (browser.equals(BrowserType.SAFARI)) {
            SafariOptions options = new SafariOptions();
            options.setUseCleanSession(true);
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(SafariOptions.CAPABILITY, options);
            wd = new SafariDriver();
            driver.set(wd);
        }

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.url"));
        login(properties.getProperty("web.username"), properties.getProperty("web.password"));
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

    private void click(By locator) {
        wd.findElement(locator).click();
    }

    private void type(By locator, String text) {
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
