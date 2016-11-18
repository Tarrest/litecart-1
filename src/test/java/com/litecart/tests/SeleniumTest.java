package com.litecart.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/17/16.
 */
public class SeleniumTest extends TestBase {

    @Test(enabled = true)
    public void test1() {
        navigateTo("http://www.google.com");
        type(By.name("q"), "webdriver");
        click(By.name("btnG"));
    }
}
