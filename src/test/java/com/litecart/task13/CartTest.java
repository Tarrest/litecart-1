package com.litecart.task13;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 12/18/16.
 */
public class CartTest {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL = "http://localhost/litecart";
    private static final String PRODUCT = "";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, CartTest.TIMEOUT,
                CartTest.SLEEP_PERIOD);
        this.driver.get(URL);
    }

    @AfterTest
    public void afterTest() {
        this.driver.quit();
    }

    @Test
    public void testProductAdd() {
        selectProduct();
        addToCart();

        verifyProductAdd();

    }

    @Test
    public void testProductRemove() {
        checkOut();
        removeFromCart();

        verifyProductRemove();
    }

    private void selectProduct() {
    }

    private void addToCart() {
    }

    private void removeFromCart() {
    }

    private void checkOut() {
    }

    private void verifyProductAdd() {
        checkOut();
    }

    private void verifyProductRemove() {
        checkOut();
    }




//    Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
//    Сценарий должен состоять из следующих частей:
//              1) открыть страницу какого-нибудь товара
//              2) добавить его в корзину
//              3) подождать, пока счётчик товаров в корзине обновится
//              4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза,
//                  чтобы в общей сложности в корзине было 3 единицы товара
//              5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
//              6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица

}