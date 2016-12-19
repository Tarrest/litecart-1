package com.litecart.task11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        fillRegistrationForm(new RegistrationData().withFirstname("firstname").withLastname("lastname"));
        submitRegistration();
        logout();
        login();

    }

//    Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart
//    (не в админке, а в клиентской части магазина).
//    Сценарий должен состоять из следующих частей:
//          1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты
//           (чтобы не конфликтовало с ранее созданными пользователями),
//          2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
//          3) повторный вход в только что созданную учётную запись,
//          4) и ещё раз выход.
//    Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
//    Проверки можно никакие не делать, только действия -- заполнение полей, нажатия на кнопки и ссылки.
//    Если сценарий дошёл до конца, то есть созданный пользователь смог выполнить вход и выход --
//    значит создание прошло успешно.
//    В форме регистрации есть капча, её нужно отключить в админке учебного приложения на вкладке Settings -> Security.


    private void initRegistration() {
        click(By.linkText("add new"));
    }

    private void fillRegistrationForm(RegistrationData registrationData) {
        type(By.name(""), registrationData.getTaxID());
        type(By.name(""), registrationData.getCompany());
        type(By.name(""), registrationData.getFirstname());
        type(By.name(""), registrationData.getLastname());
        type(By.name(""), registrationData.getAddress1());
        type(By.name(""), registrationData.getAddress2());
        type(By.name(""), registrationData.getPostcode());
        type(By.name(""), registrationData.getCity());
        type(By.name(""), registrationData.getCountry());
        type(By.name(""), registrationData.getState());
        type(By.name(""), registrationData.getEmail());
        type(By.name(""), registrationData.getPhone());
        type(By.name(""), registrationData.getDesiredPassword());
        type(By.name(""), registrationData.getConfirmPassword());
    }

    private void submitRegistration() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    private void login() {

    }

    private void logout() {

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