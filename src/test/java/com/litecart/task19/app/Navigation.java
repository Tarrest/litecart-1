package com.litecart.task19.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pshynin on 12/21/16.
 */
public class Navigation {

    private WebDriver driver;

    Navigation(WebDriver driver) {
        this.driver = driver;
    }

    public void homePage() {
        this.driver.findElement(By.cssSelector("[id=logotype-wrapper]")).click();
    }

    public void checkOut() {
        driver.findElement(By.cssSelector("#cart .link")).click();
    }
}