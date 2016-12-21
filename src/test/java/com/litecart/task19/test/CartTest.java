package com.litecart.task19.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by pshynin on 12/18/16.
 */
public class CartTest extends TestBase {

    @Test(enabled = true)
    public void testProductAdd() {
        if (app.quantity() < 3) {
            int before = app.quantity();
            app.homePage();
            app.selectProduct();
            app.addToCart();
            int after = app.quantity();

            assertEquals(before, after - 1);
        }
    }

    @Test(enabled = false)
    public void testProductRemove() {
        while (app.quantity() != 0) {
            int before = app.quantity();
            app.checkOut();
            app.removeFromCart();
            int after = app.quantity();

            assertEquals(before, after + 1);
        }
    }
}