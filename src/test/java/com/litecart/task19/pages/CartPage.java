package com.litecart.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pshynin on 12/22/16.
 */
public class CartPage {
    protected WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void removeFromCart() {
        driver.findElement(By.cssSelector("[name='remove_cart_item']")).click();
    }
}
