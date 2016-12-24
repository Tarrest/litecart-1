package com.litecart.task17;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

/**
 * Created by pshynin on 12/21/16.
 */
public class LoggsTest {

    private WebDriver driver;
    private WebDriverWait wait;
// Сделайте сценарий, который проверяет,
// не появляются ли в логе браузера сообщения при открытии страниц
// в учебном приложении, а именно -- страниц товаров в каталоге в административной панели.
// Сценарий должен состоять из следующих частей:
//  1) зайти в админку
//  2) открыть каталог, категорию, которая содержит товары
//  (страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
//  3) последовательно открывать страницы товаров и проверять,
//  не появляются ли в логе браузера сообщения (любого уровня)

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
    }
}
