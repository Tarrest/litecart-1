package com.litecart.task02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by pshynin on 11/17/16.
 */
public class PropertiesTest {
    protected WebDriverWait wait;
    private java.util.Properties properties = new java.util.Properties();
    private WebDriver driver;

    @BeforeTest
    protected void setUp() throws IOException {
        String file = "env.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
        properties.load(inputStream);

        System.getProperty(properties.getProperty("app.browser").toUpperCase(), BrowserType.FIREFOX);

        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get(properties.getProperty("web.url"));
    }

    @Test
    public void testProperties() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                .sendKeys(properties.getProperty("web.username"));
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")))
                .sendKeys(properties.getProperty("web.password"));
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login"))).click();
    }

    @AfterTest
    public void tearDown() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                }));
    }
}