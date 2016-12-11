package com.litecart.task02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by pshynin on 12/6/16.
 */
public class DriverFactory {
    // thread local driver object for webdriver
    private static DriverFactory instance = new DriverFactory();
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>()
    {
        @Override // can be replaced with other browser drivers
        protected WebDriver initialValue() {
            return new FirefoxDriver();
        }
    };

    // allow to initialize this class from outside
    private DriverFactory() {
    }

    public static DriverFactory getInstance() {
        return instance;
    }
    // call this method to get the driver object and launch the browser
    public WebDriver getDriver() {
        return this.driver.get();
    }

    // Quits the driver and closes the browser
    public void removeDriver() {
        this.driver.get().quit();
        this.driver.remove();
    }
}
