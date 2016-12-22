package com.litecart.task19.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by pshynin on 12/18/16.
 */
public class ProductAddTest extends TestBase {

    @Test(enabled = true)
    public void testProductAdd() {
        if (app.quantity() < 3) {
            int before = app.quantity();
            app.goTo().homePage();
            app.homePage().selectProduct();
            app.productPage().addToCart();
            int after = app.quantity();

            assertEquals(before, after - 1);
        }
    }
}