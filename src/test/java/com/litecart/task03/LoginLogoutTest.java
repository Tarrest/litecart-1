package com.litecart.task03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/17/16.
 */
public class LoginLogoutTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String USERNAME = "admin";
    private String PASSWORD = "admin";

    @BeforeMethod
    public void setup() {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.get("http://localhost/litecart/admin/");
    }

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

    @AfterMethod
    public void tearDown() {
        DriverFactory.getInstance().removeDriver();
    }
}