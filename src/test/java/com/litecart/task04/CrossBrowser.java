package com.litecart.task04;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by pshynin on 11/17/16.
 */
public class CrossBrowser {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private WebDriver wd;

    public void initBrowser() throws IOException {
        if (driver.get() != null) {
            wd = driver.get();
            return;
        }

        String browser = BrowserType.FIREFOX;

        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            driver.set(wd);
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            driver.set(wd);
        } else if (Objects.equals(browser, BrowserType.SAFARI)) {
            driver.set(wd);
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
}