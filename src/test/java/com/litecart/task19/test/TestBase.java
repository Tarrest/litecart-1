package com.litecart.task19.test;

import com.litecart.task19.app.Application;
import org.openqa.selenium.remote.BrowserType;
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
    protected static final Application app
            = new Application(BrowserType.FIREFOX);
    private Logger logger = LoggerFactory.getLogger(ProductAddTest.class);

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " With parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p) {
        logger.info("Stop test " + m.getName() + " With parameters " + Arrays.asList(p));
    }

    @BeforeTest
    public void setUp() {
        app.init();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
