package com.litecart.task14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by pshynin on 12/18/16.
 */
public class NewWindowTest {
    private static final String URL = "http://localhost/litecart/admin";
    private static final String URL_COUNTRIES = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private static final String COUNTRY_ADD = "";
    private static final int TIMEOUT = 30000;

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(this.driver, TIMEOUT);
        this.driver.get(URL);
        login(USERNAME, PASSWORD);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }

    @Test(enabled = true)
    public void testNewWindow() {
        this.driver.get(URL_COUNTRIES);

        List<WebElement> links = this.driver.findElements(By.cssSelector("#content a[target=_blank]"));
        String mainWindowId = this.driver.getWindowHandle();

        for (WebElement link : links) {
            String windowId = this.clickOnExternalLink(link);
            this.driver.switchTo().window(windowId);
            this.driver.close();
            this.driver.switchTo().window(mainWindowId);
        }
    }

    private String clickOnExternalLink(final WebElement link) {
        Set<String> oldWindows = this.driver.getWindowHandles();
        link.click();
        return this.wait.until((final WebDriver d) -> {
            Set<String> curWindows = this.driver.getWindowHandles();
            curWindows.removeAll(oldWindows);

            if (curWindows.size() > 0) {
                return curWindows.iterator().next();
            }

            return null;
        });
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
        click(locator);
        if (text != null) {
            String existingText = this.driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                this.driver.findElement(locator).clear();
                this.driver.findElement(locator).sendKeys(text);
            }
        }
    }


//    Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.
//    Сценарий должен состоять из следующих частей:
//              1) зайти в админку
//              2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
//              3) открыть на редактирование какую-нибудь страну или начать создание новой
//              4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой
//                  -- они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.
//    Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank".
//    Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне,
//    потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти действия для всех таких ссылок.
//    Не забудьте, что новое окно открывается не мгновенно, поэтому требуется ожидание открытия окна.

}
