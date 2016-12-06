package com.litecart.task02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class Properties {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    protected WebDriver wd;
    protected WebDriverWait wait;

    @BeforeSuite
    protected void setUp() throws IOException {
        java.util.Properties properties = new java.util.Properties();
        String file = "env.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
        properties.load(inputStream);

        String browser = System.getProperty(properties.getProperty("app.browser").toUpperCase(), BrowserType.FIREFOX);

        if (driver.get() != null) {
            wd = driver.get();
            return;
        }

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.url"));
        // login(properties.getProperty("web.username"), properties.getProperty("web.password"));
    }
}
