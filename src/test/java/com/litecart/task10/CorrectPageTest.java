package com.litecart.task10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CorrectPageTest {
    private final static String URL = "http://localhost/litecart";

    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        new WebDriverWait(this.driver, 10L);
        this.driver.get(URL);
    }

    @AfterTest
    public void afterTest() {
        this.driver.quit();
    }

    @Test
    public void testCorrectPage() {

    }

//    Сделайте сценарий, который проверяет,
// что при клике на товар открывается правильная страница товара в учебном приложении litecart.
//
//1) Открыть главную страницу
//2) Кликнуть по первому товару в категории Campaigns
//3) Проверить, что открывается страница правильного товара
//
//    Более точно, проверить, что
//    а) совпадает текст названия товара
//    б) совпадает цена (обе цены)
//
//    Кроме того, проверить стили цены на главной странице и на странице товара
//    -- первая цена серая, зачёркнутая, маленькая,
//    вторая цена красная жирная, крупная.

    private void click(By locator) {
        WebElement element = this.driver.findElement(locator);
        if (element.isDisplayed()) {
            click(locator);
        }
    }
}