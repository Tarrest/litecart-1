package com.litecart.task02;

import com.litecart.task03.DriverFactory;
import com.litecart.task03.LoginLogoutTest;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by pshynin on 11/17/16.
 */
public class BeforeAndAfter {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(LoginLogoutTest.class);

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(this.driver, BeforeAndAfter.TIMEOUT,
                BeforeAndAfter.SLEEP_PERIOD);
    }

    @AfterTest
    public void afterTest() {
        this.driver.quit();
    }

    @BeforeMethod
    public void start(){
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.get("http://www.google.com");
    }

    @AfterMethod
    public void stop(){
        DriverFactory.getInstance().removeDriver();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p) {
        logger.info("Stop test " + m.getName());
    }

    @BeforeSuite
    public void setUp() {
            String browser = BrowserType.FIREFOX;

            if (Objects.equals(browser, BrowserType.FIREFOX)) {
                driver = new FirefoxDriver();
            } else if (Objects.equals(browser, BrowserType.CHROME)) {
                driver = new ChromeDriver();
            } else if (Objects.equals(browser, BrowserType.SAFARI)) {
                driver = new SafariDriver();
            }
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                }));
    }
}