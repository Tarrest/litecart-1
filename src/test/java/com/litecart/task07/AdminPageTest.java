package com.litecart.task07;

import com.litecart.task02.TestBase;
import com.litecart.task03.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pshynin on 11/26/16.
 */
public class AdminPageTest extends TestBase {
    private static final String START_URL = "http://localhost/litecart/admin";
    private static final String MENU_ITEM_TEMPLATE = "ul#box-apps-menu li:nth-child({0})";
    private static final String SUB_MENU_ITEM_TEMPLATE = "li:nth-child({0})";
    private static final String HEADER_LOCATOR = "td#content h1";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void beforeTest() {
        this.driver = DriverFactory.getInstance().getDriver();
        this.driver.get(START_URL);
        this.wait = new WebDriverWait(this.driver, 20L);
        type(By.name("username"), "admin");
        type(By.name("password"), "admin");
        this.driver.findElement(By.name("login")).click();
    }

    @Test(enabled = true)
    public void testAdminPage() {

        List<WebElement> elements = this.driver.findElements(By.className("vfmThumbnail"));
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                element.click();
            }
        }
    }

    private void type(final By locator, final String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.wd.findElement(locator).sendKeys(text);
    }
}
