package com.litecart.task08;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pshynin on 11/26/16.
 */
public class StickersTest {
    private static final String URL = "http://localhost/litecart/admin";
    private static final String MAIN_MENU = "span.name";
    private static final String SUB_MENU = "li:nth-child({0})";
    private static final String HEADER = "td#content h1";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, 10L);
        this.driver.get(URL);
        this.login(USERNAME, PASSWORD);
    }

    @Test(enabled = true)
    public void test1() {
        List<WebElement> elements = driver.findElements(By.cssSelector("li.product"));
        SoftAssertions soft = new SoftAssertions();
        for (WebElement element : elements)
        {
            String title = element.findElement(By.tagName("a"))
                    .getAttribute("title");
            StickersTest.log.info("Verifying stickers for {}", title);
            int stickers = element.findElements(By.cssSelector("div.sticker")).size();
            Assert.assertEquals((stickers), isEqualTo(1));
        }

        soft.assertAll();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }

    private void click(By locator) {
        WebElement element = this.driver.findElement(locator);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    private void type(By locator, String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.driver.findElement(locator).sendKeys(text);
    }

    private void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.name("login"));
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