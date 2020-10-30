package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LogInPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
TODO:
-при вводе почты sign up
-может, надо соцсети покликать
-несуществующий --> sign up в ошибке переход
-тексты ошибок проверить
 */

public class LogInTest {
    private static WebDriver webDriver;
    private static LogInPage logInPage;
    private static MainPage mainPage;

    @BeforeAll
    public static void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        logInPage = new LogInPage(webDriver);
        mainPage = new MainPage(webDriver);
    }

    @Test
    public void testCorrectEmail() {
        mainPage.clickToLogIn();
        logInPage.inputEmail("yecine5178@aieen.com");
        logInPage.goNext();
        assertTrue(logInPage.isPassInputDisplayed());
    }

    @Test
    public void testIncorrectEmail1() {
        mainPage.clickToLogIn();
        logInPage.inputEmail("yecine5178@aieen.");
        logInPage.goNext();
        assertTrue(logInPage.isErrorDisplayed());
    }

    @Test
    public void testIncorrectEmail2() {
        mainPage.clickToLogIn();
        logInPage.inputEmail("yecine5178aieen.com");
        logInPage.goNext();
        assertTrue(logInPage.isErrorDisplayed());
    }

    @Test
    public void testIncorrectEmail3() {
        mainPage.clickToLogIn();
        logInPage.inputEmail("kek@ya.ru");
        logInPage.goNext();
        assertTrue(logInPage.isErrorDisplayed());
    }

    @Test
    public void testEmptyEmail() {
        mainPage.clickToLogIn();
        logInPage.inputEmail("");
        logInPage.goNext();
        assertTrue(logInPage.isErrorDisplayed());
    }

    @Test
    public void testEmptyPassword() {
        mainPage.clickToLogIn();
        logInPage.signIn("yecine5178@aieen.com", "");
        assertTrue(logInPage.isErrorForIncorrectPasswordDisplayed());
    }

    @Test
    public void testIncorrectPassword() {
        mainPage.clickToLogIn();
        logInPage.signIn("yecine5178@aieen.com", "123400000");
        assertTrue(logInPage.isErrorForIncorrectPasswordDisplayed());
    }

    @Test
    public void testCorrectPassword() {
        mainPage.clickToLogIn();
        logInPage.signIn("yecine5178@aieen.com", "12345678");
        assertTrue(mainPage.isAccountDisplayed());
    }

    //FIXME или убрать вообще
    @Test
    public void checkShowingPassword() {
        mainPage.clickToLogIn();
        logInPage.inputEmail("yecine5178@aieen.com");
        logInPage.goNext();
        logInPage.inputPassword("12345");
        logInPage.clickShowPassword();
    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}
