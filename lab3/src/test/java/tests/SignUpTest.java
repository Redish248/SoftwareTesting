package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LogInPage;
import pages.MainPage;
import pages.SignUpPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 TODO:
 -перейти в sign in
 -соцсети?
 -там ввод имени при регистрации
 */
public class SignUpTest {
    private static WebDriver webDriver;
    private static LogInPage logInPage;
    private static MainPage mainPage;
    private static SignUpPage signUpPage;

    @BeforeAll
    public static void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        logInPage = new LogInPage(webDriver);
        mainPage = new MainPage(webDriver);
        signUpPage = new SignUpPage(webDriver);
    }

    @Test
    public void testCorrectEmail() {
        mainPage.goToRegisterPage();
        signUpPage.enterEmail("test123@ya.ru");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isPasswordInputDisplayed());
    }

    @Test
    public void testEmptyEmail() {
        mainPage.goToRegisterPage();
        signUpPage.enterEmail("");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isEmailErrorDisplayed());
    }

    @Test
    public void testIncorrectEmail() {
        mainPage.goToRegisterPage();
        signUpPage.enterEmail("test@ya.");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isEmailErrorDisplayed());
    }

    @Test
    public void testIncorrectEmail2() {
        mainPage.goToRegisterPage();
        signUpPage.enterEmail("testya.ru");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isEmailErrorDisplayed());
    }

    @Test
    public void testExistingEmail() {
        mainPage.goToRegisterPage();
        signUpPage.enterEmail("yecine5178@aieen.com");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isEmailErrorDisplayed());
    }

    @Test
    public void testAllEmptyPass1() {
        mainPage.goToRegisterPage();
        signUpPage.createAccount("test123@ya.ru","", "");
        assertTrue(signUpPage.isPasswordFirstErrorDisplayed());
    }

    @Test
    public void testAllEmptyPass2() {
        mainPage.goToRegisterPage();
        signUpPage.createAccount("test123@ya.ru","", "");
        assertTrue(signUpPage.isPasswordSecondErrorDisplayed());
    }

    @Test
    public void testFirstEmptyPass() {
        mainPage.goToRegisterPage();
        signUpPage.createAccount("test123@ya.ru","", "12345678");
        assertTrue(signUpPage.isPasswordFirstErrorDisplayed());
    }

    @Test
    public void testSecondEmptyPass() {
        mainPage.goToRegisterPage();
        signUpPage.createAccount("test123@ya.ru","12345678", "");
        assertTrue(signUpPage.isPasswordSecondErrorDisplayed());
    }

    @Test
    public void testNotMatchedPass() {
        mainPage.goToRegisterPage();
        signUpPage.createAccount("test123@ya.ru","12345678", "1234");
        assertTrue(signUpPage.isPasswordFirstErrorDisplayed());
    }

    @Test
    public void testLessThanEightPass() {
        mainPage.goToRegisterPage();
        signUpPage.createAccount("test123@ya.ru","1234", "1234");
        assertTrue(signUpPage.isPasswordFirstErrorDisplayed());
    }

    //TODO: не запускайте просто так))
    @Test
    @Disabled
    public void testCorrectPass() {
        mainPage.goToRegisterPage();
        signUpPage.createAccount("test123@ya.ru","12345678", "12345678");
        assertTrue(mainPage.isAccountDisplayed());
    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }

}
