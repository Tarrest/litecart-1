package com.litecart.task01;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTest {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String SEARCH_STR = "selenium";
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, GoogleSearchTest.TIMEOUT,
                GoogleSearchTest.SLEEP_PERIOD);
    }

    @Test
    public void searchTest() {
        this.driver.get("http://google.com");
        this.driver.findElement(By.id("lst-ib"))
                .sendKeys(GoogleSearchTest.SEARCH_STR);
        this.driver.findElement(By.name("btnG")).click();
        By result = By.className("g");
        this.wait.until(ExpectedConditions.presenceOfElementLocated(result));
        Assert.assertTrue("Search results do not contain search string",
                this.driver.findElement(result).getText()
                        .contains(GoogleSearchTest.SEARCH_STR));
    }

    @After
    public void afterTest() {
        this.driver.quit();
    }

    public void click(By locator) {
        WebElement element = driver.findElement(locator);
        if (element.isDisplayed()) {
            element.click();
        }
    }

}