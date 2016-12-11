package com.litecart.task03;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by pshynin on 11/17/16.
 */
@RunWith(Parameterized.class)
public class TestBase {
    private static final long TIMEOUT_SEC = 30L;
    private static final long SLEEP_MS = 500L;
    static final String URL = "http://localhost/litecart/admin/";
    static final String USERNAME = "admin";
    static final String PASSWORD = "admin";

    WebDriver driver;
    WebDriverWait wait;

    @Parameterized.Parameter(0)
    String username = "admin";

    @Parameterized.Parameter(1)
    String password = "admin";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"admin", "admin"}});
    }

    @Before
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, TestBase.TIMEOUT_SEC,
                TestBase.SLEEP_MS);
    }

    @After
    public void tearDown() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    this.driver.quit();
                    this.driver = null;
                }));
    }

}