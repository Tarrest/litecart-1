package com.litecart.task02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class Properties {
    protected WebDriverWait wait;
    private WebDriver wd;

    @BeforeSuite
    protected void setUp() throws IOException {
        java.util.Properties properties = new java.util.Properties();
        String file = "env.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
        properties.load(inputStream);

        String browser = System.getProperty(properties.getProperty("app.browser").toUpperCase(), BrowserType.FIREFOX);

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.url"));
        wd.findElement(By.name("username")).click();
        wd.findElement(By.name("username")).clear();
        wd.findElement(By.name("username")).sendKeys(properties.getProperty("web.username"));
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys(properties.getProperty("web.password"));
        this.wd.findElement(By.name("login")).click();
    }
}