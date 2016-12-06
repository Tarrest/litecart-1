package com.litecart.task03;

import com.litecart.task02.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/17/16.
 */
public class LoginLogoutTest extends TestBase {

    @Test(enabled = true)
    public void loginTest() {
        // logout();
        Assert.assertEquals(wd.getCurrentUrl(), "http://localhost/litecart/admin/login.php");
        // login("admin", "admin");
        Assert.assertEquals(wd.getCurrentUrl(), "http://localhost/litecart/admin/");
    }
}