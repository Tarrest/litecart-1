package com.litecart.task19.app;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by pshynin on 12/20/16.
 */
public class AppManager {

    public WebDriver driver;

    public void selectProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
        for (WebElement product : products) {
            if (isElementPresent(By.cssSelector("[name='options[Size]']"))) {
                new Select(this.driver.findElement(By.cssSelector("option.value")));
            }
            product.click();
        }
    }

    public void homePage() {
        this.driver.findElement(By.cssSelector("[id=logotype-wrapper]")).click();
    }

    public void addToCart() {
        this.driver.findElement(By.cssSelector("[name=add_cart_product]")).click();
    }

    public void removeFromCart() {
        this.driver.findElement(By.cssSelector("[name='remove_cart_item']")).click();
    }

    public void checkOut() {
        this.driver.findElement(By.cssSelector("#cart .link")).click();
    }

    public int quantity() {
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
