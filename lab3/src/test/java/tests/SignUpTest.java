package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LogInPage;
import pages.MainPage;
import pages.SignUpPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 TODO:
 -перейти в sign in
 -соцсети?
 -там ввод имени при регистрации
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignUpTest {
    private WebDriver webDriver;
    private LogInPage logInPage;
    private MainPage mainPage;
    private SignUpPage signUpPage;

    @BeforeEach
    public void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        logInPage = new LogInPage(webDriver);
        mainPage = new MainPage(webDriver);
        signUpPage = new SignUpPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='onetrust-accept-btn-handler']")));
        webDriver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
        webDriver.navigate().to("https://account.booking.com/register");
    }

    @Test
    @Order(1)
    public void testCorrectEmail() {
        signUpPage.enterEmail("test123@ya.ru");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isPasswordInputDisplayed());
    }

    @Test
    @Order(2)
    public void testEmptyEmail() {
        signUpPage.enterEmail("");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isEmailErrorDisplayed());
    }

    @Test
    @Order(3)
    public void testIncorrectEmail() {
        signUpPage.enterEmail("test@ya.");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isEmailErrorDisplayed());
    }

    @Test
    @Order(4)
    public void testIncorrectEmail2() {
        signUpPage.enterEmail("testya.ru");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isEmailErrorDisplayed());
    }

    @Test
    @Order(5)
    public void testExistingEmail() {
        signUpPage.enterEmail("yecine5178@aieen.com");
        signUpPage.clickGetStarted();
        assertTrue(signUpPage.isEmailErrorDisplayed());
    }

    @Test
    @Order(6)
    public void testAllEmptyPass1() {
        signUpPage.createAccount("test123@ya.ru","", "");
        assertTrue(signUpPage.isPasswordFirstErrorDisplayed());
    }

    @Test
    @Order(7)
    public void testAllEmptyPass2() {
        signUpPage.createAccount("test123@ya.ru","", "");
        assertTrue(signUpPage.isPasswordSecondErrorDisplayed());
    }

    @Test
    @Order(8)
    public void testFirstEmptyPass() {
        signUpPage.createAccount("test123@ya.ru","", "12345678");
        assertTrue(signUpPage.isPasswordFirstErrorDisplayed());
    }

    @Test
    @Order(9)
    public void testSecondEmptyPass() {
        signUpPage.createAccount("test123@ya.ru","12345678", "");
        assertTrue(signUpPage.isPasswordSecondErrorDisplayed());
    }

    @Test
    @Order(10)
    public void testNotMatchedPass() {
        signUpPage.createAccount("test123@ya.ru","12345678", "1234");
        assertTrue(signUpPage.isPasswordSecondErrorDisplayed());
    }

    @Test
    @Order(11)
    public void testLessThanEightPass() {
        signUpPage.createAccount("test123@ya.ru","1234", "1234");
        assertTrue(signUpPage.isPasswordFirstErrorDisplayed());
    }

    //TODO: не запускайте просто так))
    @Test
    @Disabled
    @Order(12)
    public void testCorrectPass() {
        signUpPage.createAccount("test123@ya.ru","12345678", "12345678");
        assertTrue(mainPage.isAccountDisplayed());
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }

}
