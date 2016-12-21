package com.litecart.task13;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by pshynin on 12/18/16.
 */
public class CartTest {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL = "http://localhost/litecart";

    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        new WebDriverWait(this.driver, CartTest.TIMEOUT,
                CartTest.SLEEP_PERIOD);
        this.driver.get(URL);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }

    @Test(enabled = true)
    public void testProductAdd() {
        if (quantity() < 3) {
            int before = quantity();
            homePage();
            selectProduct();
            addToCart();
            int after = quantity();

            assertEquals(before, after - 1);
        }
    }

    @Test(enabled = false)
    public void testProductRemove() {
        while (quantity() != 0) {
            int before = quantity();
            checkOut();
            removeFromCart();
            int after = quantity();

            assertEquals(before, after + 1);
        }
    }

    private void selectProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
        for (WebElement product : products) {
            if (isElementPresent(By.cssSelector("[name='options[Size]']"))) {
                new Select(this.driver.findElement(By.cssSelector("option.value")));
            }
            product.click();
        }
    }

    private void homePage() {
        this.driver.findElement(By.cssSelector("[id=logotype-wrapper]")).click();
    }

    private void addToCart() {
        this.driver.findElement(By.cssSelector("[name=add_cart_product]")).click();
    }

    private void removeFromCart() {
        this.driver.findElement(By.cssSelector("[name='remove_cart_item']")).click();
    }

    private void checkOut() {
        this.driver.findElement(By.cssSelector("#cart .link")).click();
    }

    private int quantity() {
        String quantity = this.driver.findElement(By.cssSelector("#cart .quantity")).getText();
        return Integer.parseInt(quantity);
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