package com.litecart;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 12/18/16.
 */
public class PentahoTab {
	private static final int SLEEP_PERIOD = 1000;
	private static final int TIMEOUT = 30000;
	private static final String URL = "http://localhost/litecart";

	 private WebDriver driver;
	//FirefoxDriver wd;

	@BeforeTest
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// this.driver = new FirefoxDriver();
		// new WebDriverWait(this.driver, CartTest.TIMEOUT,
		// CartTest.SLEEP_PERIOD);
		// this.driver.get(URL);
	}


	@AfterTest(alwaysRun = true)
	public void afterTest() {
		driver.quit();
		// this.driver.quit();
	}

	public static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}