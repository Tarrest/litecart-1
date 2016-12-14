package com.litecart.task09;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class AdminSortTest {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL_APP = "http://localhost/litecart/admin";
    private final static String URL_COUNTRIES = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private final static String URL_GEO_ZONES = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";
    private final static String COUNTRY = "tr.row";
    private final static String COUNTRY_ZONE = "tr.row";
    private final static String GEO_ZONE = "country";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, AdminSortTest.TIMEOUT,
                AdminSortTest.SLEEP_PERIOD);
        this.driver.get(URL_APP);
        login(USERNAME, PASSWORD);
    }

    @AfterTest
    public void afterTest() {
        this.driver.quit();
    }

//    Сделайте сценарии, которые проверяют сортировку стран и геозон (штатов) в учебном приложении litecart.
//
//    1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
//    а) проверить, что страны расположены в алфавитном порядке

//    б) для тех стран, у которых количество зон отлично от нуля
// -- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке
//
//    2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
//    зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке

    @Test
    public void testCountriesOrder() {
        this.driver.get(URL_COUNTRIES);
        List<WebElement> countries = this.driver.findElements(By.cssSelector(COUNTRY));
        for (WebElement country : countries) {
            country.getAttribute("value");

            //    if (this.driver.findElements(By.cssSelector(COUNTRY_ZONE)) > 0) {
            List<WebElement> county_zones = this.driver.findElements(By.cssSelector(COUNTRY_ZONE));
            for (WebElement zone : county_zones) {
                zone.getLocation();
//                }
            }
        }
    }

    @Test
    public void testZonesOrder() {
        this.driver.get(URL_GEO_ZONES);
        List<WebElement> geo_zones = this.driver.findElements(By.cssSelector(GEO_ZONE));
        for (WebElement zone : geo_zones) {
            zone.getLocation();
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

    private boolean isElementPresent(By locator) {
        try {
            this.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


//    @Test
//    public void countriesSorting() {
//        actions.getWebDriverWait()
//                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                        By.cssSelector(CountriesSortingVerifier.COUNTRY_ROW_LOCATOR)));
//        List<WebElement> rows = actions.getWebDriver().findElements(
//                By.cssSelector(CountriesSortingVerifier.COUNTRY_ROW_LOCATOR));
//        String prev = "";
//        SoftAssertions soft = new SoftAssertions();
//        List<String> withZones = new ArrayList<>();
//        int index = 2;
//
//        for (WebElement row : rows) {
//            String next = row.findElement(By.cssSelector("td:nth-child(5)"))
//                    .getText();
//            String zones = row.findElement(By.cssSelector("td:nth-child(6)"))
//                    .getText();
//            CountriesSortingVerifier.log.info("Verifying order of " + next);
//
//            if (!"0".equals(zones)) {
//                withZones.add(MessageFormat.format(
//                        CountriesSortingVerifier.COUNTRY_LOCATOR_TEMPLATE, index));
//            }
//
//            soft.assertThat(next.compareTo(prev))
//                    .withFailMessage("'%s' should be before '%s'", prev, next)
//                    .isGreaterThanOrEqualTo(0);
//            prev = next;
//            index++;
//        }
//
//        Context.getContext().put(ContextKey.ZONES_LOCATORS,
//                withZones.toArray(new String[withZones.size()]));
//        soft.assertAll();
//    }
//
//    @Test
//    public void zonesSortingTest() {
//        Actions actions = Actions.getActions();
//
//        By countryBy = By.cssSelector(this.locator);
//        actions.getWebDriverWait().until(
//                ExpectedConditions.visibilityOfElementLocated(countryBy));
//        actions.clickAndWait(countryBy);
//        ZonesSortingVerifier.log.info("Waiting for zones table to fully load");
//        actions.getWebDriverWait()
//                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                        By.cssSelector(ZonesSortingVerifier.DATA_TABLE_ROWS)));
//        List<WebElement> rows = actions.getWebDriver().findElements(
//                By.cssSelector(ZonesSortingVerifier.DATA_TABLE_ROWS));
//        int last = rows.size() - 1;
//        String prev = "";
//        SoftAssertions soft = new SoftAssertions();
//
//        for (int i = 1; i < last; i++) {
//            WebElement row = rows.get(i);
//            String next = row.findElement(By.cssSelector("td:nth-child(3)"))
//                    .getText();
//            ZonesSortingVerifier.log.info("Verifying order of " + next);
//            soft.assertThat(next.compareTo(prev))
//                    .withFailMessage("'%s' should be before '%s'", prev, next)
//                    .isGreaterThanOrEqualTo(0);
//            prev = next;
//        }
//
//        ZonesSortingVerifier.log.info("Go back to countries list");
//        actions.getWebDriver().navigate().back();
//        soft.assertAll();
//    }
}