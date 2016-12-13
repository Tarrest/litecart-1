package com.litecart.task07;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by pshynin on 12/9/16.
 */
public class AdminPageTest {
    private static final String URL = "http://localhost/litecart/admin";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private static final String MAIN_MENU = "span.name";
    private static final String SUB_MENU = "li:nth-child({0})";
    private static final String HEADER = "td#content h1";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, 10L);
        this.driver.get(URL);
        this.login(USERNAME, PASSWORD);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }

    @Test(enabled = false)
    public void Test1() {
        List<WebElement> elements = this.driver.findElements(By.cssSelector(MAIN_MENU));
        Iterator<WebElement> element = elements.iterator();

        while (element.hasNext()) {
            List<WebElement> mainMenu = this.driver.findElements(By.cssSelector(MAIN_MENU));
            Iterator<WebElement> mainMenuElement = mainMenu.iterator();
            mainMenuElement.next().click();

            if (isElementPresent(By.cssSelector(SUB_MENU))) {
                List<WebElement> subMenuElements = this.driver.findElements(By.cssSelector(SUB_MENU));
                Iterator<WebElement> subMenuElement = subMenuElements.iterator();
                subMenuElement.next().click();

                assertTrue(isElementPresent(By.cssSelector(HEADER)));
            }
        }
    }

    @Test(enabled = true)
    public void Test2() {
        List<WebElement> mainMenu = this.driver.findElements(By.cssSelector(MAIN_MENU));
        for (WebElement mainElement : mainMenu) {
            mainElement.click();

            assertTrue(isElementPresent(By.cssSelector(HEADER)));

            if (isElementPresent(By.className(SUB_MENU))) {
                List<WebElement> subMenu = this.driver.findElements(By.className(SUB_MENU));
                for (WebElement subElement : subMenu) {
                    subElement.click();

                    assertTrue(isElementPresent(By.cssSelector(HEADER)));
                }
            }
        }
    }

    private void click(By locator) {
        WebElement element = this.driver.findElement(locator);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    private void type(By locator, String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.driver.findElement(locator).sendKeys(text);
    }

    private void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.name("login"));
    }

    private boolean isElementPresent(By locator) {
        try {
            this.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
