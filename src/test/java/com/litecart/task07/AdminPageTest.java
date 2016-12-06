package com.litecart.task07;

import com.litecart.task02.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/26/16.
 */
public class AdminPageTest extends TestBase {

    @Test(enabled = true)
    public void testAdminPage() {
        wd.findElement(By.xpath(".//*[@id='app-']/a/span[2]")).click();
//        verifyHeader(By.className("Template"), By.cssSelector("h1"));
//        verifyHeader(By.className("Logotype"), By.cssSelector("h1"));
//
//        click(By.className("Catalog"));
//        verifyHeader(By.className("Catalog"), By.cssSelector("h1"));
//        verifyHeader(By.className("Product Groups"), By.cssSelector("h1"));
//        verifyHeader(By.className("Option Groups"), By.cssSelector("h1"));
//        verifyHeader(By.className("Manufacturers"), By.cssSelector("h1"));
//        verifyHeader(By.className("Suppliers"), By.cssSelector("h1"));
//        verifyHeader(By.className("Delivery Statuses"), By.cssSelector("h1"));
//        verifyHeader(By.className("Sold Out Statuses"), By.cssSelector("h1"));
//        verifyHeader(By.className("Quantity Units"), By.cssSelector("h1"));
//        verifyHeader(By.className("CSV Import/Export"), By.cssSelector("h1"));
//
//        verifyHeader(By.linkText("Countries"), By.cssSelector("h1"));
//
//        verifyHeader(By.linkText("Currencies"), By.cssSelector("h1"));
//
//        verifyHeader(By.linkText("Customers"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Newsletter"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Newsletter"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Newsletter"), By.cssSelector("h1"));
//
//        verifyHeader(By.linkText("Geo Zones"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Languages"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Storage Encoding"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Modules"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Background Jobs"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Customer"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Shipping"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Payment"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Order Total"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Order Success"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Order Action"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Orders"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Order Statuses"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Pages"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Reports"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Monthly Sales"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Most Sold Products"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Most Shopping Customers"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Settings"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Store Info"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Defaults"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("General"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Listings"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Images"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Checkout"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Advanced"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Security"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Slides"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Tax"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Tax Classes"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Tax Rates"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Translations"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Search Translations"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Scan Files"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("CSV Import/Export"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("Users"), By.cssSelector("h1"));
//        verifyHeader(By.linkText("vQmods"), By.cssSelector("h1"));
//        verifyHeader(By.xpath("//li[@id='doc-vqmods']//span[.='vQmods']"), By.cssSelector("h1"));

    }
}
