package com.litecart.task03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by pshynin on 11/17/16.
 */
@RunWith(Parameterized.class)
public class LoginTest {
    private static final long TIMEOUT_SEC = 30L;
    private static final long SLEEP_MS = 500L;
    private static final String URL = "http://localhost/litecart/admin/";

    @Parameterized.Parameter(1)
    public String username = "admin";

    @Parameterized.Parameter(2)
    public String password = "admin";

    public WebDriver driver;
    public WebDriverWait wait;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"admin", "admin"}});
    }

    @Before
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, LoginTest.TIMEOUT_SEC,
                LoginTest.SLEEP_MS);
    }

    @Test
    public void runTest() {
        this.driver.get(LoginTest.URL);
        this.driver.findElement(By.name("username")).click();
        this.driver.findElement(By.name("username")).clear();
        this.driver.findElement(By.name("username")).sendKeys(username);
        this.driver.findElement(By.name("password")).click();
        this.driver.findElement(By.name("password")).clear();
        this.driver.findElement(By.name("password")).sendKeys(password);
        By loginBy = By.name("login");
        this.driver.findElement(loginBy).click();
        By logoutBy = By.xpath("//a[@title='Logout']");
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBy));
        this.driver.findElement(logoutBy).click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(loginBy));
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