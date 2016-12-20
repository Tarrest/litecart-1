package com.litecart.task12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by pshynin on 12/18/16.
 */
public class NewProductTest {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL = "http://localhost/litecart/admin";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String PRODUCT_NAME = "nameTest1";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, NewProductTest.TIMEOUT,
                NewProductTest.SLEEP_PERIOD);
        this.driver.get(URL);
        login(USERNAME, PASSWORD);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }

    @Test(enabled = true)
    public void testNewProduct() {
        initProductAdd();
        fillGeneralForm(new ProductData().withCode("codeTest1")
                .withName(PRODUCT_NAME).withDescription("descriptionTest1"));
        fillInformationForm(new ProductData().withKeywords("keywordsTest1")
                .withQuantity("quantityTest1").withTitle("titleTest1"));
        fillPricesForm(new ProductData().withPrice("priceTest1"));

        List<WebElement> products = this.driver.findElements(By.cssSelector(".dataTable td:nth-child(3) a"));
        for (WebElement product : products) {
            assertEquals(PRODUCT_NAME, product.getText());
        }
    }

    private void initProductAdd() {
        click(By.cssSelector("#box-apps-menu li a[href*='doc=catalog']"));
        click(By.cssSelector("#content div a:last-child"));
    }

    private void initGeneralForm() {
        click(By.cssSelector("a[href*=tab-general]"));
    }

    private void initInformationForm() {
        click(By.cssSelector("a[href*=tab-information]"));
    }

    private void initPricesForm() {
        click(By.cssSelector("a[href*=tab-prices]"));
    }

    private void submitProductAdd() {
        click(By.cssSelector("[name=save]"));
    }

    private void fillGeneralForm(ProductData productData) {
        initGeneralForm();
        type(By.cssSelector("[name*=name]"), productData.getName());
        type(By.cssSelector("[name=code]"), productData.getCode());
        type(By.cssSelector("[name=quantity]"), productData.getQuantity());
        submitProductAdd();
    }

    private void fillInformationForm(ProductData productData) {
        initInformationForm();
        type(By.cssSelector("[name=keywords]"), productData.getKeywords());
        type(By.cssSelector(".trumbowyg-editor"), productData.getDescription());
        type(By.cssSelector("[name*=head_title]"), productData.getTitle());
        submitProductAdd();
    }

    private void fillPricesForm(ProductData productData) {
        initPricesForm();
        type(By.cssSelector("[name='prices[USD]']"), productData.getPrice());
        submitProductAdd();
    }

    private void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.name("login"));
    }

    private void click(By locator) {
        WebElement element = this.driver.findElement(locator);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    private void type(By locator, String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.driver.findElement(locator).clear();
        this.driver.findElement(locator).sendKeys(text);
    }

    private class ProductData {
        private String name;
        private String code;
        private String quantity;
        private String keywords;
        private String description;
        private String title;
        private String price;

        String getName() {
            return name;
        }

        String getCode() {
            return code;
        }

        String getQuantity() {
            return quantity;
        }

        String getKeywords() {
            return keywords;
        }

        String getDescription() {
            return description;
        }

        String getTitle() {
            return title;
        }

        String getPrice() {
            return price;
        }

        ProductData withName(String name) {
            this.name = name;
            return this;
        }

        ProductData withCode(String code) {
            this.code = code;
            return this;
        }

        ProductData withQuantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        ProductData withKeywords(String keywords) {
            this.keywords = keywords;
            return this;
        }

        ProductData withDescription(String description) {
            this.description = description;
            return this;
        }

        ProductData withTitle(String title) {
            this.title = title;
            return this;
        }

        ProductData withPrice(String price) {
            this.price = price;
            return this;
        }
    }
}