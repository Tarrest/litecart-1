package com.litecart.task06;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class task6 {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String SEARCH_STR = "selenium";
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, task6.TIMEOUT,
                task6.SLEEP_PERIOD);
    }

    @Test
    public void runTest() {
        this.driver.get("http://google.com");
        this.driver.findElement(By.id("lst-ib"))
                .sendKeys(task6.SEARCH_STR);
        this.driver.findElement(By.name("btnG")).click();
        By result = By.className("g");
        this.wait.until(ExpectedConditions.presenceOfElementLocated(result));
        Assert.assertTrue("Search results do not contain search string",
                this.driver.findElement(result).getText()
                        .contains(task6.SEARCH_STR));
    }

    @After
    public void afterTest() {
        this.driver.quit();
    }

}