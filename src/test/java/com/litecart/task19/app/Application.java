package com.litecart.task19.app;

import com.litecart.task19.pages.CartPage;
import com.litecart.task19.pages.HomePage;
import com.litecart.task19.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

/**
 * Created by pshynin on 12/20/16.
 */
public class Application {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL = "http://localhost/litecart";

    private Navigation goTo;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    private WebDriver driver;
    private String browser;

    public Application(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            this.driver = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.CHROME)) {
            this.driver = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.SAFARI)) {
            this.driver = new SafariDriver();
        }
        Navigation goTo = new Navigation(driver);
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        new WebDriverWait(this.driver, TIMEOUT, SLEEP_PERIOD);
        driver.get(URL);
    }

    public void stop() {
        driver.quit();
    }

    public int quantity() {
        String quantity = driver.findElement(By.cssSelector("#cart .quantity")).getText();
        return Integer.parseInt(quantity);
    }

    public Navigation goTo() {
        return goTo;
    }

    public HomePage homePage() {
        return homePage;
    }

    public ProductPage productPage() {
        return productPage;
    }

    public CartPage cartPage() {
        return cartPage;
    }
}
