package com.litecart.task11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by pshynin on 12/14/16.
 */
public class RegistrationTest {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;
    private static final String URL = "http://localhost/litecart";

    private static final String EMAIL_ADDRESS = "email@Test1";
    private static final String PASSWORD = "passwordTest1";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, RegistrationTest.TIMEOUT,
                RegistrationTest.SLEEP_PERIOD);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        this.driver.quit();
    }

    @Test(enabled = true)
    public void testRegistration() {
        this.driver.get(URL);
        initRegistration();
        fillRegistrationForm(new RegistrationData().withTaxID("1234").withCompany("companyTest1")
                .withFirstname("firstnameTest1").withLastname("lastnameTest1").withAddress1("addressTest1")
                .withAddress2("addressTest2").withPostcode("02000").withCity("Boston").withCountry("United States")
                .withState("Massachusets").withEmail(EMAIL_ADDRESS).withPhone("123-123-12-12")
                .withDesiredPassword(PASSWORD).withConfirmPassword(PASSWORD));
        submitRegistration();
        logout();
        login(EMAIL_ADDRESS, PASSWORD);
    }

    private void initRegistration() {
        click(By.cssSelector("form[name='login_form'] a"));
    }

    private void submitRegistration() {
        click(By.cssSelector("button[name='create_account']"));
    }

    private void fillRegistrationForm(RegistrationData registrationData) {
        type(By.cssSelector("input[name='tax_id']"), registrationData.getTaxID());
        type(By.cssSelector("input[name='company']"), registrationData.getCompany());
        type(By.cssSelector("input[name='firstname']"), registrationData.getFirstname());
        type(By.cssSelector("input[name='lastname']"), registrationData.getLastname());
        type(By.cssSelector("input[name='address1']"), registrationData.getAddress1());
        type(By.cssSelector("input[name='address2']"), registrationData.getAddress2());
        type(By.cssSelector("input[name='postcode']"), registrationData.getPostcode());
        type(By.cssSelector("input[name='city']"), registrationData.getCity());
        select(By.cssSelector("input[name='country_code']"), registrationData.getCountry());
        select(By.cssSelector("input[name='zone_code']"), registrationData.getState());
        type(By.cssSelector("input[name='email']"), registrationData.getEmail());
        type(By.cssSelector("input[name='phone']"), registrationData.getPhone());
        type(By.cssSelector("input[name='password']"), registrationData.getDesiredPassword());
        type(By.cssSelector("input[name='confirmed_password']"), registrationData.getConfirmPassword());
    }

    private void login(String emailaddress, String password) {
        type(By.cssSelector("[name='email']"), emailaddress);
        type(By.cssSelector("[name='password']"), password);
        click(By.cssSelector("[name='login']"));
    }

    private void logout() {
        click(By.cssSelector("#box-account li:last-child a"));
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

    private void select(By locator, String text) {
        Select element = new Select(driver.findElement(locator));
        element.selectByVisibleText(text);
    }

    private class RegistrationData {
        private String taxID;
        private String company;
        private String firstname;
        private String lastname;
        private String address1;
        private String address2;
        private String postcode;
        private String city;
        private String country;
        private String state;
        private String email;
        private String phone;
        private String desiredPassword;
        private String confirmPassword;

        String getTaxID() {
            return taxID;
        }

        String getCompany() {
            return company;
        }

        String getFirstname() {
            return firstname;
        }

        String getLastname() {
            return lastname;
        }

        String getAddress1() {
            return address1;
        }

        String getAddress2() {
            return address2;
        }

        String getPostcode() {
            return postcode;
        }

        String getCity() {
            return city;
        }

        String getCountry() {
            return country;
        }

        String getState() {
            return state;
        }

        String getEmail() {
            return email;
        }

        String getPhone() {
            return phone;
        }

        String getDesiredPassword() {
            return desiredPassword;
        }

        String getConfirmPassword() {
            return confirmPassword;
        }

        RegistrationData withTaxID(String taxID) {
            this.taxID = taxID;
            return this;
        }

        RegistrationData withCompany(String company) {
            this.company = company;
            return this;
        }

        RegistrationData withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        RegistrationData withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        RegistrationData withAddress1(String address1) {
            this.address1 = address1;
            return this;
        }

        RegistrationData withAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        RegistrationData withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        RegistrationData withCity(String city) {
            this.city = city;
            return this;
        }

        RegistrationData withCountry(String country) {
            this.country = country;
            return this;
        }

        RegistrationData withState(String state) {
            this.state = state;
            return this;
        }

        RegistrationData withEmail(String email) {
            this.email = email;
            return this;
        }

        RegistrationData withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        RegistrationData withDesiredPassword(String desiredPassword) {
            this.desiredPassword = desiredPassword;
            return this;
        }

        RegistrationData withConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        @Override
        public String toString() {
            return "RegistrationData{" +
                    "taxID='" + taxID + '\'' +
                    ", Company='" + company + '\'' +
                    ", firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", address1='" + address1 + '\'' +
                    ", address2='" + address2 + '\'' +
                    ", postcode='" + postcode + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", state='" + state + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", desiredPassword='" + desiredPassword + '\'' +
                    ", confirmPassword='" + confirmPassword + '\'' +
                    '}';
        }
    }
}