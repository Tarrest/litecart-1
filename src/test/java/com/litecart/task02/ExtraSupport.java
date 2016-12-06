package com.litecart.task02;

import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtraSupport {
    private WebDriver wd;
    private WebDriverWait wait;

    protected void click(By locator) {
        WebElement element = wd.findElement(locator);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    private void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    private void type2(final By locator, final String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.wd.findElement(locator).sendKeys(text);
    }

    protected void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.name("login"));
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
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
