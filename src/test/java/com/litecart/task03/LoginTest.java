package com.litecart.task03;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LoginTest {
    private static final long TIMEOUT_SEC = 30L;
    private static final long SLEEP_MS = 500L;
    private static final String URL = "http://localhost/litecart/admin/";

    @Parameterized.Parameter(1)
    public String username = "admin";

    @Parameterized.Parameter(2)
    public String password = "admin";

    public WebDriver wd;
    public WebDriverWait wait;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"admin", "admin"}});
    }

    @Before
    public void beforeTest() {
        this.wd = new FirefoxDriver();
        this.wait = new WebDriverWait(this.wd, LoginTest.TIMEOUT_SEC,
                LoginTest.SLEEP_MS);
    }

    @Test
    public void runTest() {
        this.wd.get(LoginTest.URL);
        wd.findElement(By.name("username")).click();
        wd.findElement(By.name("username")).clear();
        wd.findElement(By.name("username")).sendKeys(username);
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys(password);
        By loginBy = By.name("login");
        this.wd.findElement(loginBy).click();
        By logoutBy = By.xpath("//a[@title='Logout']");
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBy));
        this.wd.findElement(logoutBy).click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(loginBy));
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