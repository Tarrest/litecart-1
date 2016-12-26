package com.litecart.task17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Level;

/**
 * Created by pshynin on 12/21/16.
 */
public class LoggsTest {
    private final static String URL = "http://localhost/litecart/admin";
    private final static String CATALOG = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";
    private static final String PRODUCT = "table.dataTable td:nth-child(3) a[href*='edit_product']";

    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, prefs);
        driver = new ChromeDriver(caps);
        wait = new WebDriverWait(this.driver, TIMEOUT, SLEEP_PERIOD);
        driver.get(URL);
        login(USERNAME, PASSWORD);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }

    @Test(enabled = true)
    public void testLogs() {
        driver.navigate().to(CATALOG);

        List<WebElement> products = driver.findElements(By.cssSelector(PRODUCT));
        for (WebElement product : products) {
            product = driver.findElement(By.cssSelector(PRODUCT));
            product.click();

            LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
            for (LogEntry log : logs) {
                System.out.println(log.getTimestamp() + " " + log.getLevel() + " " + log.getMessage());
            }

            driver.navigate().back();
        }
    }

    private void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.name("login"));
    }

    private void click(By locator) {
        WebElement element = this.driver.findElement(locator);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    private void type(By locator, String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.driver.findElement(locator).clear();
        this.driver.findElement(locator).sendKeys(text);
    }
}
