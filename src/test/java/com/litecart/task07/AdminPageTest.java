package com.litecart.task07;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import static java.awt.SystemColor.text;

/**
 * Created by pshynin on 11/26/16.
 */
public class AdminPageTest {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL = "http://localhost/litecart/admin/login.php";
    private static final String MENU_ITEM = "ul#box-apps-menu li:nth-child({0})";
    private static final String SUB_MENU_ITEM = "li:nth-child({0})";
    private static final String HEADER = "td#content h1";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;


    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, AdminPageTest.TIMEOUT
                , AdminPageTest.SLEEP_PERIOD);
    }

    @Test(enabled = true)
    public void testAdminPage() {
        this.driver.get(URL);
        login(USERNAME, PASSWORD);

//        Собираешь лист элементов с одинаковым локатором и по нему циклом.
        //от Первого Листа берёшь количество итераций - длину списка.

//        Внутри иф условие, если есть вложенное меню, то опять лист, и по нему вторым циклом.
        //        Второй Лист заводишь в цикл. Потому что его надо обновлять каждый раз.
//        В этом же цикле проверка заголовка.


//        List<WebElement> elements = driver.findElements(By.cssSelector(MENU_ITEM));
//        int s = elements.size();
//        for(int i=1;i<=s;i++){
//            By.cssSelector(MessageFormat
//                            .format(AdminPageTest.MENU_ITEM, mainIndex++)
//            elements = driver.findElements(By.xpath("//div[@id='...']/ul/li"));
//            elements.get(i).click();
//        }
//        for (WebElement element : elements) {
//            if (element.isDisplayed()) {
//                element.click();
//            }
//        }

//        List<WebElement> menu = driver.findElements(By.cssSelector(SUB_MENU_ITEM));
//        for (WebElement option : menu) {
//            WebElement header = driver.findElement(By.cssSelector(HEADER));
//            Assert.assertTrue(header.contains(text));
//            driver.quit();
//        }

//        List<WebElement> mainMenu = driver.findElements(By.cssSelector(MENU_ITEM));
//        Iterator<WebElement> main = mainMenu.iterator();
//        while(main.hasNext()) {
//            if ()
//            List<WebElement> subMenu = driver.findElements(By.cssSelector(SUB_MENU_ITEM));
//            Iterator<WebElement> sub = subMenu.iterator();
//            sub.next().click();
//        }
    }

    @Test
    public void runTest() {
        this.driver.get(AdminPageTest.URL);
        this.login("admin", "admin");
        int mainIndex = 1;
        boolean bMainMenuItemPresent = true;
        while (bMainMenuItemPresent) {
            // Searching main menu items by index, until none found.
            By itemBy = By.cssSelector(MessageFormat
                    .format(AdminPageTest.MENU_ITEM, mainIndex++));
 //           bMainMenuItemPresent = this.actions.findElemens(itemBy);

            if (bMainMenuItemPresent) {
                // Click on main menu item.
                click(itemBy);
                boolean bSubMenuItemPresent = true;
                int subIndex = 1;

                while (bSubMenuItemPresent) {
                    // Searching for sub menu items, if any.
                    By subMenuItem = By.cssSelector(MessageFormat.format(
                            AdminPageTest.SUB_MENU_ITEM,
                            subIndex++));
                    WebElement mainMenuItem = this.driver.findElement(itemBy);
//                    bSubMenuItemPresent = this.actions.findSubElement(mainMenuItem,
//                            subMenuItem);

                    if (bSubMenuItemPresent) {
                        // if found - click on it and verify presence of header.
                        mainMenuItem.findElement(subMenuItem).click();
//                        Assert.assertTrue("Header not found!",
//                                this.actions.findElemens(By.cssSelector(
//                                        AdminPageTest.HEADER)));
                    }
                }
            }
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                }));
    }

    private void click(By locator) {
        WebElement element = this.driver.findElement(locator);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    private void type(final By locator, final String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.driver.findElement(locator).sendKeys(text);
    }

    private void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        this.driver.findElement(By.name("login")).click();
    }
}