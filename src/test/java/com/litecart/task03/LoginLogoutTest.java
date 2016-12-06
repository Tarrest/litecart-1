package com.litecart.task03;

import com.litecart.task02.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/17/16.
 */
public class LoginLogoutTest extends TestBase {

    @Test(enabled = true)
    public void loginTest() {

        wd.get("http://localhost/litecart/admin/");

        type(By.name("username"), "username");
        type(By.name("password"), "password");
        wd.findElement(By.name("login")).click();
        Assert.assertEquals(wd.getCurrentUrl(), "http://localhost/litecart/admin/");

        wd.findElement(By.xpath("//a[@title='Logout']")).click();
        Assert.assertEquals(wd.getCurrentUrl(), "http://localhost/litecart/admin/login.php");
    }

    private void type(By locator, String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.wd.findElement(locator).sendKeys(text);
    }
}