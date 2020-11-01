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

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
TODO:
-при вводе почты sign up
-может, надо соцсети покликать
-несуществующий --> sign up в ошибке переход
-тексты ошибок проверить
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogInTest {
    private WebDriver webDriver;
    private LogInPage logInPage;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logInPage = new LogInPage(webDriver);
        mainPage = new MainPage(webDriver);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='onetrust-accept-btn-handler']")));
        webDriver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
        webDriver.navigate().to("https://account.booking.com/sign-in");
    }

    @Test
    @Order(1)
    public void testCorrectEmail() {
        logInPage.inputEmail("yecine5178@aieen.com");
        logInPage.goNext();
        assertTrue(logInPage.isPassInputDisplayed());
    }

    @Test
    @Order(2)
    public void testIncorrectEmail1() {
        logInPage.inputEmail("yecine5178@aieen.");
        logInPage.goNext();
        assertTrue(logInPage.isErrorDisplayed());
    }

    @Test
    @Order(3)
    public void testIncorrectEmail2() {
        logInPage.inputEmail("yecine5178aieen.com");
        logInPage.goNext();
        assertTrue(logInPage.isErrorDisplayed());
    }

    @Test
    @Order(4)
    public void testIncorrectEmail3() {
        logInPage.inputEmail("kek@ya.ru");
        logInPage.goNext();
        assertTrue(logInPage.isErrorDisplayed());
    }

    @Test
    @Order(5)
    public void testEmptyEmail() {
        logInPage.inputEmail("");
        logInPage.goNext();
        assertTrue(logInPage.isErrorDisplayed());
    }

    @Test
    @Order(6)
    public void testEmptyPassword() {
        logInPage.signIn("yecine5178@aieen.com", "");
        assertTrue(logInPage.isErrorForIncorrectPasswordDisplayed());
    }

    @Test
    @Order(7)
    public void testIncorrectPassword() {
        logInPage.signIn("yecine5178@aieen.com", "123400000");
        assertTrue(logInPage.isErrorForIncorrectPasswordDisplayed());
    }

    @Test
    @Order(8)
    public void testCorrectPassword() {
        logInPage.signIn("yecine5178@aieen.com", "12345678");
        assertTrue(mainPage.isAccountDisplayed());
    }

    //FIXME или убрать вообще
    @Test
    @Disabled
    public void checkShowingPassword() {
        logInPage.inputEmail("yecine5178@aieen.com");
        logInPage.goNext();
        logInPage.inputPassword("12345");
        logInPage.clickShowPassword();
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }
}
