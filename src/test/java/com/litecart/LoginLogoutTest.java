package com.litecart;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/17/16.
 */
public class LoginLogoutTest extends TestBase {

    @Test(enabled = false)
    public void loginTest() {
        Assert.assertEquals(wd.getCurrentUrl(), "http://localhost/litecart/admin/");
    }

    @Test(enabled = false)
    public void logoutTest() {
        logout();
        Assert.assertEquals(wd.getCurrentUrl(), "http://localhost/litecart/admin/login.php");
    }
}