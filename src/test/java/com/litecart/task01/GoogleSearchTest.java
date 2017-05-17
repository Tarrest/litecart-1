package com.litecart.task01;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 11/17/16.
 */
public class GoogleSearchTest {
    private static final String URL = "http://google.com";
    private static final String PENTAHO_URL = "http://10.177.176.152:8080/pentaho/Login";
    private static final String PENTAHO_WEB_URL = "http://10.177.176.152:8080/pentaho/api/repos/%3Ahome%3Aadmin%3AA-%5B~!%40%23%24%25%5E%26*()%7B%7D%7C.%2C%5D-%3D_%2B%7C%3B%3F%3C%3E~%60%09_06123752.xanalyzer/editor?ts=1494955560763";
    private static final String PENTAHO_XML_URL = "http://10.177.176.152:8080/pentaho/api/repos/%3Ahome%3Aadmin%3AA-%5B~!%40%23%24%25%5E%26*()%7B%7D%7C.%2C%5D-%3D_%2B%7C%3B%3F%3C%3E~%60%09_06123752.xanalyzer/parameter?ts=1494955560763";


    private static final String FIELD = "#lst-ib";
    private static final String BUTTON = "#_fZl";
    private static final String RESULT = "#res";
    private static final String SEARCH = "selenium";

    private static final int SLEEP_PERIOD = 3000;
    private static final int TIMEOUT = 30000;

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, GoogleSearchTest.TIMEOUT,
                GoogleSearchTest.SLEEP_PERIOD);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                }));
    }

    @Test(enabled = true)
    public void testGoogleSearch() {
        this.driver.get(URL);
        this.driver.findElement(By.cssSelector(FIELD)).sendKeys(SEARCH);
        this.driver.findElement(By.cssSelector(BUTTON)).click();

        this.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(RESULT)));
        Assert.assertTrue(this.driver.findElement(By.cssSelector(RESULT)).getText().contains(SEARCH));
    }

    /*
     *  Pentaho project try selenium 3
     */
    @Test
    public void test() {
        driver.get(PENTAHO_URL);
        driver.findElement(By.xpath("//div[@id='eval-users-toggle']//div[.='Login as an Evaluator']")).click();
        driver.findElement(By.xpath("//div[@id='role-admin-panel']//button[.='GO']")).click();

        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
        driver.findElement(By.linkText(PENTAHO_XML_URL)).sendKeys(selectLinkOpeninNewTab);

        driver.get(PENTAHO_WEB_URL);
        driver.get(PENTAHO_XML_URL);

        driver.findElement(By.cssSelector("div.custom-dropdown-label")).click();
        driver.findElement(By.id("gwt-uid-9")).click();
        driver.findElement(By.cssSelector("div.pentaho-tabWidgetLabel")).click();
        driver.findElement(By.id("openTabInNewWindow")).click();
        driver.findElement(By.id("okButton")).click();
    }
}

        /*
        Подготовьте инфраструктуру для выполнения домашних заданий:
            1) Создайте репозиторий на сервисе GitHub, в который будут отправляться домашние задания.
                Для тех, кто раньше не работал с этим сервисом, внизу есть видео-инструкция.
                (любители C# при желании могут использовать сервис visualstudio.com,
                в этом случае не забудьте предоставить доступ тренерам к репозиторию --
                barancev@gmail.com и igor.lyubin@outlook.com).
            2) Установите всё необходимое для работы программное обеспечение.
            3) Создайте проект внутри локальной копии репозитория, подключите к проекту Selenium
                и сделайте небольшой работающий тест, который просто запускает браузер,
                открывает какую-нибудь страницу и закрывает браузер.
            4) Уложите всё в репозиторий и синхронизируйте с удалённым сервером.
         */