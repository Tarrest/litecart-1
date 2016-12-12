package com.litecart.task01;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String SEARCH_STR = "selenium";
    private static final String URL = "http://google.com";

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
        this.driver.findElement(By.id("lst-ib"))
                .sendKeys(GoogleSearchTest.SEARCH_STR);
        this.driver.findElement(By.name("btnG")).click();
        By result = By.className("g");
        this.wait.until(ExpectedConditions.presenceOfElementLocated(result));
        Assert.assertTrue(this.driver.findElement(result).getText()
                        .contains(GoogleSearchTest.SEARCH_STR),
                "Search results do not contain search string");
    }

    @Test(enabled = true)
    public void test2() {
        this.driver.navigate().to(URL);
        this.driver.findElement(By.name("q")).sendKeys("webdriver");
        Assert.assertFalse(isElementPresent(By.name("XXX")));
    }

    @Test(enabled = true)
    public void test3() {
        this.driver.navigate().to("http://www.google.com");
        this.driver.findElement(By.name("q")).sendKeys("webdriver");
        Assert.assertFalse(this.isElementPresent(By.xpath("//div[")));
    }

    private boolean isElementPresent(By locator) {
        try {
            this.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}