package com.litecart.task03;

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
//        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        this.driver.findElement(By.name("username")).sendKeys(USERNAME);
//        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        this.driver.findElement(By.name("password")).sendKeys(PASSWORD);
        this.driver.findElement(By.name("login")).click();
        Assert.assertEquals(this.driver.getCurrentUrl(), "http://localhost/litecart/admin/");

        this.driver.findElement(By.xpath("//a[@title='Logout']")).click();
        Assert.assertEquals(this.driver.getCurrentUrl(), "http://localhost/litecart/admin/login.php");
    }

    @Test(enabled = true)
    public void runTest() {
        this.driver.get(TestBase.URL);
        this.driver.findElement(By.name("username")).click();
        this.driver.findElement(By.name("username")).clear();
        this.driver.findElement(By.name("username")).sendKeys(username);
        this.driver.findElement(By.name("password")).click();
        this.driver.findElement(By.name("password")).clear();
        this.driver.findElement(By.name("password")).sendKeys(password);
        By loginBy = By.name("login");
        this.driver.findElement(loginBy).click();
        By logoutBy = By.xpath("//a[@title='Logout']");
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBy));
        this.driver.findElement(logoutBy).click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(loginBy));
    }
}