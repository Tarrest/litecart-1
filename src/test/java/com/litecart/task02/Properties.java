package com.litecart.task02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by pshynin on 11/17/16.
 */
public class Properties {
    protected WebDriverWait wait;
    private WebDriver driver;

    @BeforeSuite
    protected void setUp() throws IOException {
        java.util.Properties properties = new java.util.Properties();
        String file = "env.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
        properties.load(inputStream);

        String browser = System.getProperty(properties.getProperty("app.browser").toUpperCase(), BrowserType.FIREFOX);

        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get(properties.getProperty("web.url"));

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        this.driver.findElement(By.name("username")).sendKeys(properties.getProperty("web.username"));
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        this.driver.findElement(By.name("password")).sendKeys(properties.getProperty("web.password"));
        this.driver.findElement(By.name("login")).click();
    }
}