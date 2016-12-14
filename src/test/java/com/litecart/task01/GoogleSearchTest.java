package com.litecart.task01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/17/16.
 */
public class GoogleSearchTest {
    private static final String URL = "http://google.com";
    private static final String FIELD = "#lst-ib";
    private static final String BUTTON = "#_fZl";
    private static final String RESULT = "div.f";
    private static final String SEARCH = "selenium";

    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, GoogleSearchTest.TIMEOUT,
                GoogleSearchTest.SLEEP_PERIOD);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                }));
    }

    @Test(enabled = true)
    public void test1() {
        this.driver.get(URL);
        this.driver.findElement(By.cssSelector(FIELD)).sendKeys(SEARCH);
        this.driver.findElement(By.cssSelector(BUTTON)).click();
        this.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(RESULT)));
        Assert.assertTrue(this.driver.findElement(By.cssSelector(RESULT))
                .getText().contains(SEARCH), "Search results not found");
    }
}