package com.litecart.task02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    protected WebDriver wd;
    protected WebDriverWait wait;

    @BeforeSuite
    protected void setUp() throws IOException {

        String browser = BrowserType.FIREFOX;

        if (driver.get() != null) {
            wd = driver.get();
            return;
        }

        switch (browser) {
            case BrowserType.FIREFOX: {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(FirefoxDriver.MARIONETTE, false);
                wd = new FirefoxDriver(caps);
                driver.set(wd);
                break;
            }
            case BrowserType.CHROME: {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-fullscreen");
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(ChromeOptions.CAPABILITY, options);
                wd = new ChromeDriver();
                driver.set(wd);
                break;
            }
            case BrowserType.IE:
                wd = new InternetExplorerDriver();
                driver.set(wd);
                break;
            case BrowserType.SAFARI: {
                SafariOptions options = new SafariOptions();
                options.setUseCleanSession(true);
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(SafariOptions.CAPABILITY, options);
                wd = new SafariDriver();
                driver.set(wd);
                break;
            }
        }

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    wd.quit();
                    wd = null;
                }));
    }
}
