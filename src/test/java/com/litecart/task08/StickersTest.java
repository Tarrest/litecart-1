package com.litecart.task08;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by pshynin on 11/26/16.
 */
public class StickersTest {
    private static final String URL = "http://localhost/litecart";
    private static final String PRODUCT = "li.product";
    private static final String STICKER = "div.sticker";

    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        new WebDriverWait(this.driver, 10L);
        this.driver.get(URL);
    }

    @Test(enabled = true)
    public void testStickers() {
        List<WebElement> products = driver.findElements(By.cssSelector(PRODUCT));
        for (WebElement product : products) {
            assertEquals(product.findElements(By.cssSelector(STICKER)).size(), 1);
        }
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }
}