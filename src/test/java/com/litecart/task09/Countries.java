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

/**
 * Created by pshynin on 12/16/16.
 */
public class Countries {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL_APP = "http://localhost/litecart/admin";
    private final static String URL_COUNTRIES = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private final static String COUNTRY = "//form[@name='countries_form']//td[5]/a";
    private final static String COUNTRY_ZONE = "//form[@name='countries_form']//td[6]";
    private final static String ZONE_ROW = "//*[@id='table-zones']//tr/td[3]";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, Countries.TIMEOUT,
                Countries.SLEEP_PERIOD);
        this.driver.get(URL_APP);
        login(USERNAME, PASSWORD);
    }

    @AfterTest (alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }

    @Test(enabled = true)
    public void testCountriesOrder() {
        this.driver.get(URL_COUNTRIES);
        List<WebElement> countries = this.driver.findElements(By.xpath(COUNTRY));
        List<String> before = new ArrayList<>();
        List<String> after = new ArrayList<>();

        for (WebElement country : countries) {
            before.add(country.getText());
            after.add(country.getText());

            Collections.sort(after);
            assertEquals(after, before);
        }
    }

    @Test(enabled = true)
    public void testCountryZonesOrder() {
        this.driver.get(URL_COUNTRIES);
        List<WebElement> countryZones = driver.findElements(By.xpath(COUNTRY_ZONE));
        countryZones.stream().filter(zone -> Integer.parseInt(zone.getText()) != 0).forEach(zone -> {
            zone.click();

            List<WebElement> zoneRows = driver.findElements(By.cssSelector(ZONE_ROW));
            zoneRows.remove(zoneRows.size() - 1);

            List<String> before = new ArrayList<>();
            List<String> after = new ArrayList<>();

            for (WebElement row : zoneRows) {
                before.add(row.getText());
                after.add(row.getText());
            }

            Collections.sort(after);
            assertEquals(after, before);
        });
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