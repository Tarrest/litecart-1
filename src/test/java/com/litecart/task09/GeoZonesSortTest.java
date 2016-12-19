package com.litecart.task09;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GeoZonesSortTest {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL_APP = "http://localhost/litecart/admin";
    private final static String URL_GEO_ZONE = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";
    private final static String COUNTRY = "//form[@name='geo_zones_form']//td[3]/a";
    private final static String COUNTRY_ZONE = "//*[@id='table-zones']//td[3]/select/option[@selected='selected']";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, GeoZonesSortTest.TIMEOUT,
                GeoZonesSortTest.SLEEP_PERIOD);
        this.driver.get(URL_APP);
        login(USERNAME, PASSWORD);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }

    @Test
    public void testGeoZonesOrder() {
        driver.get(URL_GEO_ZONE);
        List<WebElement> countries = driver.findElements(By.xpath(COUNTRY));
        for (WebElement country : countries) {
            country.click();

            List<WebElement> zones = driver.findElements(By.xpath(COUNTRY_ZONE));
            List<String> before = new ArrayList<>();
            List<String> after = new ArrayList<>();

            for (WebElement zone : zones) {
                before.add(zone.getText());
                after.add(zone.getText());
            }

            Collections.sort(after);
            assertEquals(after, before);
        }
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
}