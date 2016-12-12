package com.litecart.task02;

import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by pshynin on 11/17/16.
 */
public class ExtraSupport {
    private WebDriver driver;
    private WebDriverWait wait;

    protected void click(By locator) {
        WebElement element = this.driver.findElement(locator);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    private void type1(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = this.driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                this.driver.findElement(locator).clear();
                this.driver.findElement(locator).sendKeys(text);
            }
        }
    }

    private void type2(final By locator, final String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.driver.findElement(locator).sendKeys(text);
    }

    protected void login(String username, String password) {
        type1(By.name("username"), username);
        type1(By.name("password"), password);
        click(By.name("login"));
    }

    public boolean isAlertPresent() {
        try {
            this.driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent1(By locator) {
        try {
            this.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isElementPresent2(WebDriver driver, By locator) {
        return this.driver.findElements(locator).size() > 0;
    }

    public boolean isElementPresent3(By locator) {
        try {
            this.driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException ex) {
            throw ex;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isElementPresent4(By locator) {
//        wait = new WebDriverWait(driver, 10);
        try {
//            wait.until(WebDriver d); -> d.findElement(locator);
            this.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean areElementsPresent(WebDriver driver, By locator) {
        return this.driver.findElements(locator).size() > 0;
    }

    private void waitForAjax() {
        this.wait.until(new Predicate<WebDriver>() {

            @Override
            public boolean apply(final WebDriver input) {
                return (Boolean) ((JavascriptExecutor) input)
                        .executeScript("return jQuery.active == 0");
            }
        });
    }
}
