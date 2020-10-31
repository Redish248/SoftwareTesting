package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.SearchPage;

public class SearchPageTest {
    private static WebDriver webDriver;
    private static MainPage mainPage;
    private static SearchPage searchPage;

    @BeforeAll
    public static void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        searchPage = new SearchPage(webDriver);
        mainPage = new MainPage(webDriver);
    }

    @Test
    public void testEmptyCity() {
        mainPage.goToSuggestion(mainPage.getSuggestions().get(0));

    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}
