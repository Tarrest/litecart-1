package com.litecart.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/17/16.
 */
public class SeleniumTest extends TestBase {

    @Test(enabled = true)
    public void navigateTest() {
        navigateTo("https://www.google.com/");
        String url = wd.getCurrentUrl();
        Assert.assertEquals(url, "https://www.google.com/");
    }

    @Test(enabled = true)
    public void searchTest() {
        navigateTo("https://www.google.com/?gws_rd=ssl");
        type(By.name("q"), "Google");
        click(By.name("btnG"));
        String title = wd.getTitle();
        Assert.assertEquals(title, "Google");
    }
}