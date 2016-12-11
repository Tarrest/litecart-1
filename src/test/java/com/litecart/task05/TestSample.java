package com.litecart.task05;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 12/7/16.
 */
public class TestSample {
private TestBase tb;

    @Test
    public void myFirstTest() {
        tb.driver.navigate().to("http://www.google.com");
        tb.driver.findElement(By.name("q")).sendKeys("webdriver");
        Assert.assertFalse(tb.isElementPresent(By.xpath("//div[")));
//        WebElement btnG = driver.findElement(By.name("btnG"));
//        btnG.click();
//        wait.until(titleIs("webdriver - Google Search"));
    }
}