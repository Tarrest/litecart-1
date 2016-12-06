package com.litecart.task03;

import com.google.common.base.Predicate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LoginTest {
    private static final long TIMEOUT_SEC = 30L;
    private static final long SLEEP_MS = 500L;
    private static final String URL = "http://localhost/litecart/admin/";

    @Parameterized.Parameter(0)
    private String username = "admin";

    @Parameterized.Parameter(1)
    private String password = "admin";

    private WebDriver driver;
    private WebDriverWait wait;

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
        this.type(By.name("username"), this.username);
        this.type(By.name("password"), this.password);
        By loginBy = By.name("login");
        this.driver.findElement(loginBy).click();
        this.waitForAjax();
        By logoutBy = By.xpath("//a[@title='Logout']");
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBy));
        this.driver.findElement(logoutBy).click();
        this.waitForAjax();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(loginBy));
    }

    @After
    public void afterTest() {
        this.driver.quit();
    }

    private void type(final By by, final String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        this.driver.findElement(by).sendKeys(text);
    }

    private void waitForAjax() {
        this.wait.until(new Predicate<WebDriver>() {

            @Override
            public boolean apply(final WebDriver input) {
                return (Boolean) ((JavascriptExecutor) input)
                        .executeScript("return jQuery.active == 0");
            }
        });
    }
}