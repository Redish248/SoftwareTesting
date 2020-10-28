package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {
    private static WebDriver webDriver;
    private static MainPage mainPage;

    @BeforeAll
    public static void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        mainPage = new MainPage(webDriver);
    }

    @Test
    public void testDestinationField() {
        mainPage.enterCity("Казань");
        assertEquals("Казань", mainPage.destinationInput.getAttribute("value"));

    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}
