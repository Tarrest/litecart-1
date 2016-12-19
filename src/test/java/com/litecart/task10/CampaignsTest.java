package com.litecart.task10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by pshynin on 12/18/16.
 */
public class CampaignsTest {
    private final static String URL = "http://localhost/litecart";
    private final static String PRODUCT = "#box-campaigns li.product";
    private final static String PRODUCT_NAME = "div.name";
    private final static String REGULAR_PRICE = ".regular-price";
    private final static String CAMPAIN_PRICE = ".campaign-price";
    private final static String PRODUCT_DETAILES = "#box-product .price-wrapper";
    private final static String DETAILES_TITLE = "#box-product h1";

    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        new WebDriverWait(this.driver, 10L);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }

    @Test(enabled = true)
    public void testCompaigns() {
        this.driver.get(URL);
        ProductData mainPage = new ProductData();
        WebElement mainPageElement = this.driver.findElement(By.cssSelector(PRODUCT));
        mainPage.productName = mainPageElement.findElement(By.cssSelector(PRODUCT_NAME)).getText();
        mainPage.campaignPrice = mainPageElement.findElement(By.cssSelector(CAMPAIN_PRICE)).getText();
        mainPage.regularPrice = mainPageElement.findElement(By.cssSelector(REGULAR_PRICE)).getText();

        mainPageElement.click();

        ProductData productPage = new ProductData();
        WebElement productPageElement = driver.findElement(By.cssSelector(PRODUCT_DETAILES));
        productPage.productName = productPageElement.findElement(By.cssSelector(DETAILES_TITLE)).getText();
        productPage.campaignPrice = productPageElement.findElement(By.cssSelector(CAMPAIN_PRICE)).getText();
        productPage.regularPrice = productPageElement.findElement(By.cssSelector(REGULAR_PRICE)).getText();

        assertEquals(mainPage, productPage);
    }

    private class ProductData {
        String productName;
        String regularPrice;
        String campaignPrice;

        @Override
        public String toString() {
            return "ProductData{" +
                    "productName='" + productName + '\'' +
                    ", regularPrice='" + regularPrice + '\'' +
                    ", campaignPrice='" + campaignPrice + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProductData that = (ProductData) o;

            return productName != null ? productName.equals(that.productName) : that.productName == null
                    && (regularPrice != null ? regularPrice.equals(that.regularPrice) : that.regularPrice == null
                    && (campaignPrice != null ? campaignPrice.equals(that.campaignPrice) : that.campaignPrice == null));

        }

        @Override
        public int hashCode() {
            int result = productName != null ? productName.hashCode() : 0;
            result = 31 * result + (regularPrice != null ? regularPrice.hashCode() : 0);
            result = 31 * result + (campaignPrice != null ? campaignPrice.hashCode() : 0);
            return result;
        }
    }
}