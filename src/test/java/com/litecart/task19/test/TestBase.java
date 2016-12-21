package com.litecart.task19.test;

import com.litecart.task19.app.AppManager;
import com.litecart.task19.test.CartTest;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by pshynin on 12/20/16.
 */
public class TestBase {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL = "http://localhost/litecart";

    final AppManager app = new AppManager();

    private Logger logger = LoggerFactory.getLogger(CartTest.class);

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " With parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p) {
        logger.info("Stop test " + m.getName() + " With parameters " + Arrays.asList(p));
    }

    @BeforeTest
    public void beforeTest() {
        app.driver = new FirefoxDriver();
        new WebDriverWait(app.driver, TIMEOUT,
                SLEEP_PERIOD);
        app.driver.get(URL);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        app.driver.quit();
    }

}
