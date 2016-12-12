package com.litecart.task10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CorrectPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, 10L);
    }

    @Test
    public void testCorrectPage() {
        this.driver.quit();
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
}