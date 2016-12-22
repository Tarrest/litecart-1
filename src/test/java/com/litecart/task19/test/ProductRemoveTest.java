package com.litecart.task19.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by pshynin on 12/21/16.
 */
public class ProductRemoveTest extends TestBase{

    @Test(enabled = false)
    public void testProductRemove() {
        while (app.quantity() != 0) {
            int before = app.quantity();
            app.goTo().checkOut();
            app.cartPage().removeFromCart();
            int after = app.quantity();

            assertEquals(before, after + 1);
        }
    }
}
