package com.litecart.task04;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CrossBrowser {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String SEARCH_STR = "selenium";
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private WebDriver wd;
    private WebDriverWait wait;
    private String browser;

    @Before
    public void init() throws IOException {
        if (driver.get() != null){
            wd = driver.get();
            return;
        }

        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.SAFARI)) {
            wd = new SafariDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get("web.baseUrl");
        wd.findElement(By.name("username")).clear();
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("login")).click();
    }

    @Test
    public void runTest() {
        this.wd.get("http://google.com");
        this.wd.findElement(By.id("lst-ib"))
                .sendKeys(CrossBrowser.SEARCH_STR);
        this.wd.findElement(By.name("btnG")).click();
        By result = By.className("g");
        this.wait.until(ExpectedConditions.presenceOfElementLocated(result));
        Assert.assertTrue("Search results do not contain search string",
                this.wd.findElement(result).getText()
                        .contains(CrossBrowser.SEARCH_STR));
    }

    @After
    public void afterTest() {
        this.wd.quit();
    }

}