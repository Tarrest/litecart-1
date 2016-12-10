package com.litecart.task05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by pshynin on 12/7/16.
 */
public class TestClass extends BaseClass {

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        Assert.assertFalse(isElementPresent(By.xpath("//div[")));
//        WebElement btnG = driver.findElement(By.name("btnG"));
//        btnG.click();
//        wait.until(titleIs("webdriver - Google Search"));
    }
}