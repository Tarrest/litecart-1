package com.litecart.task02;

import com.litecart.task03.LoginLogoutTest;
import com.litecart.task03.LoginTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BeforeAndAfter {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private WebDriver wd;
    private Logger logger = LoggerFactory.getLogger(LoginLogoutTest.class);


    @BeforeTest
    public void beforeTest() {
        this.wd = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(this.wd, BeforeAndAfter.TIMEOUT,
                BeforeAndAfter.SLEEP_PERIOD);
    }

    @AfterTest
    public void afterTest() {
        this.wd.quit();
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
                wd = new FirefoxDriver();
            } else if (Objects.equals(browser, BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (Objects.equals(browser, BrowserType.SAFARI)) {
                wd = new SafariDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    wd.quit();
                    wd = null;
                }));
    }

}