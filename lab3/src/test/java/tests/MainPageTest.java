package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.LogInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.SignUpPage;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {
    private static WebDriver webDriver;
    private static MainPage mainPage;
    private static LogInPage logInPage;
    private static SignUpPage signUpPage;

    @BeforeAll
    public static void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        mainPage = new MainPage(webDriver);
        logInPage = new LogInPage(webDriver);
        signUpPage = new SignUpPage(webDriver);
    }

    @Test
    public void testDestinationEmptyCityNotEmptySuggestions() {
        mainPage.focusCity();
        assertNotEquals(0, mainPage.getSuggestions().size());
    }

    @Test
    public void testDestinationRealCityCheckValue() {
        mainPage.enterCity("Казань");
        assertEquals("Казань", mainPage.destinationInput.getAttribute("value"));

    }

    @Test
    public void testDestinationRealCityNotEmptySuggestions() {
        mainPage.enterCity("Казань");
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(mainPage.DESTINATION_SUGGESTIONS), 0));
        assertNotEquals(0, mainPage.getSuggestions().size());
    }

    @Test
    public void testDestinationRealCityFirstSuggestion() {
        mainPage.enterCity("Казань");
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(mainPage.DESTINATION_SUGGESTIONS), 0));
        WebElement firstSuggestion = mainPage.getSuggestions().get(0);
        assertEquals("Казань", firstSuggestion.getText());
    }

    @Test
    public void testDestinationRealCityWithMistake() {
        mainPage.enterCity("Казаньб");
        assertNotEquals(0, mainPage.getSuggestions().size());

    }

    @Test
    @Disabled
    public void testDestinationFakeCity() {
        mainPage.enterCity("Йцукен");
        assertEquals(0, mainPage.getSuggestions().size());

    }

    @Test
    public void testGoToLogInPage() {
        mainPage.clickToLogIn();
        assertTrue(logInPage.isEmailInputDisplayed());
    }

    @Test
    public void testGoToSignInPage() {
        mainPage.goToRegisterPage();
        assertTrue(signUpPage.isEmailInputDisplayed());
    }

    @Test
    public void testLogOut() {
        mainPage.clickToLogIn();
        logInPage.signIn("yecine5178@aieen.com", "12345678");
        mainPage.signOutClick();
        assertTrue(mainPage.isMainSearchDisplayed());
    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}
