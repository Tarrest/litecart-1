package com.litecart.task14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by pshynin on 12/18/16.
 */
public class NewWindowTest {
    private static final String URL = "http://localhost/litecart/admin";
    private static final String COUNTRIES = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private static final String EDIT_COUNTRY = "a[href*=countries][title=Edit]";
    private static final String LINKS = "#content a[target=_blank]";

    private static final int TIMEOUT = 30000;

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, TIMEOUT);
        driver.get(URL);
        login(USERNAME, PASSWORD);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }

    @SuppressWarnings("deprecation")
	@Test(enabled = true)
    public void testNewWindow() {
        driver.get(COUNTRIES);

        List<WebElement> countries = driver.findElements(By.cssSelector(EDIT_COUNTRY));
        for (WebElement edit : countries) {
            edit.click();

            List<WebElement> links = driver.findElements(By.cssSelector(LINKS));

            for (WebElement link : links) {
                Set<String> windowsBefore = driver.getWindowHandles();
                link.click();
                assertTrue(wait.until(ExpectedConditions
                        .numberOfwindowsToBe(windowsBefore.size() + 1)));

                Set<String> windowsAll = driver.getWindowHandles();
                windowsAll.removeAll(windowsBefore);
                driver.switchTo().window(windowsAll.iterator().next());
                driver.close();
                assertTrue(wait.until(ExpectedConditions
                        .numberOfwindowsToBe(windowsAll.size() - 1)));

                driver.switchTo().window(windowsBefore.iterator().next());
                Set<String> windowsAfter = driver.getWindowHandles();

                assertEquals(windowsAfter, windowsBefore);
            }
        }
    }

    private void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.name("login"));
    }

    private void click(By locator) {
        WebElement element = driver.findElement(locator);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    private void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }
}
