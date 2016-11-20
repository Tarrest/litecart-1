package com.litecart;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/17/16.
 */
public class LoginTest extends TestBase {

    @Test(enabled = true)
    public void loginTest() {
        wd.get("http://localhost/litecart/admin/login.php");
        login("admin", "admin");
        Assert.assertEquals(wd.getCurrentUrl(), "http://localhost/litecart/admin/");
    }

    @Test(enabled = true)
    public void logoutTest() {
        wd.get("http://localhost/litecart/admin/login.php");
        login("admin", "admin");
        logout();
        Assert.assertEquals(wd.getCurrentUrl(), "http://localhost/litecart/admin/login.php");

    }
}