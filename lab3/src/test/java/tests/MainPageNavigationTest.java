package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LogInPage;
import pages.MainPage;
import pages.SearchPage;
import pages.SignUpPage;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageNavigationTest {
    private static WebDriver webDriver;
    private static MainPage mainPage;
    private static LogInPage logInPage;
    private static SignUpPage signUpPage;
    private static SearchPage searchPage;
    private static final WebDriverConfiguration.Browser browser = WebDriverConfiguration.Browser.CHROME;

    @BeforeEach
    public void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(browser);
        mainPage = new MainPage(webDriver);
        logInPage = new LogInPage(webDriver);
        signUpPage = new SignUpPage(webDriver);
        searchPage = new SearchPage(webDriver);
        if (mainPage.isCookiesBannerOpen()) {
            mainPage.acceptCookies();
        }
    }

    @Test
    @Order(1)
    public void testPostcardCity() {
        String city = mainPage.getPostcardCityName();
        mainPage.postcard.click();
        assertAll(
                () -> assertTrue(webDriver.getCurrentUrl().contains("booking.com/searchresults.ru")),
                () -> assertEquals(city, searchPage.getCity())
        );
    }

    @Test
    @Order(2)
    public void testInputValuesSearchPage() {
        String city = "Казань";
        mainPage.enterCity(city);
        mainPage.setCheckInCheckOut(1);
        List<String> dates = mainPage.getGuestsDates();
        List<Integer> guestsAmount = mainPage.getGuestsAmount();
        mainPage.submitButton.click();
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//form[@id='frm']")));
        assertAll(
                () -> assertEquals(city, searchPage.getCity()),
                () -> assertEquals(dates, searchPage.getGuestsDates()),
                () -> assertEquals(guestsAmount, searchPage.getGuestsAmount())
        );
    }

    @Test
    @Order(3)
    public void testGoToLogInPage() {
        mainPage.clickToLogIn();
        assertTrue(logInPage.isEmailInputDisplayed());
    }

    @Test
    @Order(4)
    public void testGoToSignInPage() {
        mainPage.goToRegisterPage();
        assertTrue(signUpPage.isEmailInputDisplayed());
    }

    @Test
    @Order(5)
    public void testLogOut() {
        mainPage.clickToLogIn();
        logInPage.signIn("yecine5178@aieen.com", "12345678");
        mainPage.signOutClick();
        assertThrows(NoSuchElementException.class, () -> mainPage.isAccountDisplayed());
    }

    @Test
    @Order(6)
    public void testDestinationFakeCitySubmit() {
        mainPage.enterCity("Йцукен");
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.attributeToBe(mainPage.cityUlBlocks.get(1),
                        "class", mainPage.CITY_LOADING_CLASS));
        mainPage.submitButton.click();
        assertTrue(mainPage.isSearchErrorVisible());
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }
}
