package com.litecart.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pshynin on 12/21/16.
 */
public class ProductPage {
    public WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart() {
        driver.findElement(By.cssSelector("[name=add_cart_product]")).click();
    }
}
